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

/**
 * Response of http requests.
 *
 * @author delight.wjk@gmail.com
 */
public class UrlResponse {

  private int statusCode;
  private String content;

  /**
   * Constructs a new {@link UrlResponse} instance.
   *
   * @param statusCode The status code specified.
   * @param content The content string specified.
   */
  public UrlResponse(final int statusCode, final String content) {
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
