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

import com.google.common.collect.Lists;
import com.google.inject.servlet.GuiceServletContextListener;

import org.testng.annotations.Test;

import java.util.AbstractMap;
import java.util.List;
import java.util.UUID;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Unit tests for {@link UrlFetcher} class.
 *
 * @author jiakuan.wang@gmail.com
 */
public class UrlFetcherTest extends AbstractHttpServerSupport {

  /**
   * Tests {@link UrlFetcher#execute()} method, which should return the welcome message
   * when GET method is used..
   *
   * @throws Exception If uncaught errors occur.
   */
  @Test
  public final void testExecute_shouldReturnWelcomeMessageWithGetMethod() throws Exception {
    // Given
    final UrlFetcher urlFetcher = new UrlFetcher()
        .setUrl(getBaseUrl() + "/testget")
        .setRequestMethod(UrlFetcher.RequestMethod.GET);

    // When
    final UrlFetcher.Response response = urlFetcher.execute();

    // Then
    final int expectedCode = 200;
    assertEquals(response.getStatusCode(), expectedCode);
    assertEquals(response.getContent(), TestWelcomeServlet.WELCOME_MESSAGE);
  }

  /**
   * Tests {@link UrlFetcher#execute()} method, which should return the welcome message
   * when GET method is used..
   *
   * @throws Exception If uncaught errors occur.
   */
  @Test
  public final void testExecute_shouldReturnQueryStringWithPostMethod() throws Exception {
    // Given
    final List<AbstractMap.SimpleEntry<String, String>> params = buildParams();
    final UrlFetcher urlFetcher = new UrlFetcher().setUrl(getBaseUrl() + "/testpost")
        .setRequestMethod(UrlFetcher.RequestMethod.POST);
    for (final AbstractMap.SimpleEntry<String, String> param : params) {
      urlFetcher.addParam(param.getKey(), param.getValue());
    }

    // When
    final UrlFetcher.Response response = urlFetcher.execute();

    // Then
    final int expectedCode = 200;
    assertEquals(response.getStatusCode(), expectedCode);
    for (final AbstractMap.SimpleEntry<String, String> param : params) {
      assertTrue(response.getContent().contains(param.getKey() + "=" + param.getValue()));
    }
  }

  /**
   * Builds parameters for tests.
   *
   * @return The parameters created.
   */
  private List<AbstractMap.SimpleEntry<String, String>> buildParams() {
    final List<AbstractMap.SimpleEntry<String, String>> params = Lists.newArrayList();
    params.add(new AbstractMap.SimpleEntry<>("param1", UUID.randomUUID().toString()));
    params.add(new AbstractMap.SimpleEntry<>("param2", UUID.randomUUID().toString()));
    params.add(new AbstractMap.SimpleEntry<>("param3", UUID.randomUUID().toString()));
    params.add(new AbstractMap.SimpleEntry<>("param4", UUID.randomUUID().toString()));
    return params;
  }

  @Override
  protected final GuiceServletContextListener getGuiceServletContextListener() {
    return new TestAppGuiceConfig();
  }

  @Override
  protected final String getWelcomeServiceMessage() {
    return TestWelcomeServlet.WELCOME_MESSAGE;
  }
}
