/*
 * Copyright (c) 2016 WisePersist.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wisepersist.apuava.urlfetch;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;

import org.apache.commons.io.IOUtils;
import org.wisepersist.apuava.resource.ResourceHandler;
import org.wisepersist.apuava.resource.ResourceManager;
import org.wisepersist.apuava.resource.VoidResourceHandler;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

/**
 * URL Fetcher which can send GET and POST requests using core JDK API. It can be used
 * in Google AppEngine.
 *
 * @author jiakuan.wang@gmail.com
 */
@SuppressWarnings("ClassDataAbstractionCoupling")
public class UrlFetcher {

  private static final int READ_TIMEOUT = 10000;
  private static final int CONNECT_TIMEOUT = 15000;

  private String url;
  private int readTimeout = UrlFetcher.READ_TIMEOUT;
  private int connectTimeout = UrlFetcher.CONNECT_TIMEOUT;
  private RequestMethod requestMethod = RequestMethod.GET;
  private List<AbstractMap.SimpleEntry<String, String>> params = new ArrayList<>();
  private ResourceManager resourceManager = new ResourceManager();

  /**
   * Specifies URL to this URL fetcher.
   *
   * @param theUrl The URL specified.
   * @return This URL fetcher.
   */
  public final UrlFetcher setUrl(final String theUrl) {
    this.url = theUrl;
    return this;
  }

  /**
   * Specifies read timeout to this URL fetcher.
   *
   * @param theReadTimeout The read timeout specified.
   * @return This URL fetcher.
   */
  public final UrlFetcher setReadTimeout(final int theReadTimeout) {
    this.readTimeout = theReadTimeout;
    return this;
  }

  /**
   * Specifies connect timeout to this URL fetcher.
   *
   * @param theConnectTimeout The connect timeout specified.
   * @return This URL fetcher.
   */
  public final UrlFetcher setConnectTimeout(final int theConnectTimeout) {
    this.connectTimeout = theConnectTimeout;
    return this;
  }

  /**
   * Specifies request method to this URL fetcher.
   *
   * @param theRequestMethod The request method specified.
   * @return This URL fetcher.
   */
  public final UrlFetcher setRequestMethod(final RequestMethod theRequestMethod) {
    this.requestMethod = theRequestMethod;
    return this;
  }

  /**
   * Adds a new parameter to this URL fetcher.
   *
   * @param name The parameter name specified.
   * @param value The parameter value specified.
   * @return This URL fetcher.
   */
  public final UrlFetcher addParam(final String name, final String value) {
    this.params.add(new AbstractMap.SimpleEntry<>(name, value));
    return this;
  }

  /**
   * Executes the fetching logic.
   *
   * @return The response of the HTTP request.
   * @throws IOException If IO errors occur.
   */
  public final Response execute() throws IOException {
    Preconditions.checkNotNull(url);
    final HttpURLConnection conn = prepareConnection();
    conn.connect();
    return readResponse(conn);
  }

  /**
   * Prepares a {@link HttpURLConnection} instance with variables specified in this URL fetcher.
   *
   * @return The {@link HttpURLConnection} instance created.
   * @throws IOException If IO errors occur.
   */
  private HttpURLConnection prepareConnection() throws IOException {
    final String queryStr = getQuery(params);
    if (requestMethod == RequestMethod.GET) {
      url = url + "?" + queryStr;
    }
    final URL urlObj = new URL(url);
    final HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
    conn.setReadTimeout(readTimeout);
    conn.setConnectTimeout(connectTimeout);
    conn.setRequestMethod(requestMethod.name());
    conn.setDoInput(true);
    conn.setDoOutput(true);
    if (requestMethod == RequestMethod.POST) {
      writeToConnection(conn, queryStr);
    }
    return conn;
  }

  /**
   * Reads response from the HTTP connection.
   *
   * @param conn The HTTP connection specified.
   * @return Response object.
   * @throws IOException If IO errors occur.
   */
  private Response readResponse(final HttpURLConnection conn) throws IOException {
    final int responseCode = conn.getResponseCode();
    final String content = readContent(conn);
    return new Response(responseCode, content);
  }

  /**
   * Gets query string of parameters.
   *
   * @return The query string of parameters.
   * @throws UnsupportedEncodingException If encoding errors occur.
   */
  public final String getQueryString() throws UnsupportedEncodingException {
    return getQuery(params);
  }

  /**
   * Reads string content from the {@link HttpURLConnection} object.
   *
   * @param conn The {@link HttpURLConnection} object specified.
   * @return The string content.
   * @throws IOException If IO errors occur.
   */
  private String readContent(final HttpURLConnection conn) throws IOException {
    final InputStream inputStream = conn.getInputStream();
    return resourceManager.with(inputStream).run(new ResourceHandler<String>() {
      @Override
      public String handle(final Closeable closeable) throws IOException {
        return IOUtils.toString(inputStream, Charsets.UTF_8);
      }
    });
  }

  /**
   * Writes body data string to the {@link HttpURLConnection} object.
   *
   * @param conn The {@link HttpURLConnection} object specified.
   * @param bodyData The body data string specified.
   * @throws IOException If IO errors occur.
   */
  private void writeToConnection(final HttpURLConnection conn, final String bodyData)
      throws IOException {
    final OutputStream outputStream = conn.getOutputStream();
    resourceManager.with(outputStream).run(new VoidResourceHandler() {
      @Override
      public void handle(final Closeable closeable) throws IOException {
        final BufferedWriter writer =
            new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        resourceManager.with(writer).run(new VoidResourceHandler() {
          @Override
          public void handle(final Closeable closeable) throws IOException {
            writer.write(bodyData);
            writer.flush();
          }
        });
      }
    });
  }

  /**
   * Gets query string from parameters specified.
   *
   * @param theParams The parameters specified.
   * @return The query string created.
   * @throws UnsupportedEncodingException If encoding related errors occur.
   */
  private String getQuery(final List<AbstractMap.SimpleEntry<String, String>> theParams)
      throws UnsupportedEncodingException {
    final StringBuilder result = new StringBuilder();
    boolean first = true;
    for (final AbstractMap.SimpleEntry<String, String> pair : theParams) {
      if (first) {
        first = false;
      } else {
        result.append("&");
      }
      result.append(URLEncoder.encode(pair.getKey(), "UTF-8"));
      result.append("=");
      result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
    }
    return result.toString();
  }

  /**
   * Defines request method.
   *
   * @author jiakuan.wang@gmail.com
   */
  public enum RequestMethod {
    /**
     * Http GET method.
     */
    GET,
    /**
     * Http POST method.
     */
    POST
  }

  /**
   * Response of http requests.
   *
   * @author jiakuan.wang@gmail.com
   */
  public static class Response {

    private int statusCode;
    private String content;

    /**
     * Constructs a new {@link Response} instance.
     *
     * @param statusCode The status code specified.
     * @param content The content string specified.
     */
    public Response(final int statusCode, final String content) {
      this.statusCode = statusCode;
      this.content = content;
    }

    public final int getStatusCode() {
      return statusCode;
    }

    public final String getContent() {
      return content;
    }
  }
}
