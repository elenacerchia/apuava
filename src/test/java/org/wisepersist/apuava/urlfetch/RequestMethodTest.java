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

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Unit tests for {@link RequestMethod} class.
 *
 * @author delight.wjk@gmail.com
 */
public class RequestMethodTest {

  /**
   * Tests {@link RequestMethod#values()} method.
   *
   * @throws Exception If uncaught errors occur.
   */
  @Test
  public final void testValues() throws Exception {
    final RequestMethod[] values = RequestMethod.values();
    assertEquals(values.length, 2);
  }

  /**
   * Tests {@link RequestMethod#valueOf(String)} method.
   *
   * @throws Exception If uncaught errors occur.
   */
  @Test
  public final void testValueOf() throws Exception {
    final RequestMethod requestMethod = RequestMethod.POST;
    final RequestMethod valueOf = RequestMethod.valueOf(requestMethod.name());
    assertEquals(valueOf, requestMethod);
  }
}
