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

package org.wisepersist.apuava.resource;

import java.io.Closeable;
import java.io.IOException;

/**
 * Void resource handler interface whose implementation will contain actual resource
 * processing logic.
 *
 * @author jiakuan.wang@gmail.com
 */
public interface VoidResourceHandler {

  /**
   * Handles the resource without any return value.
   *
   * @param closeable The closeable resource specified.
   * @throws IOException If IO errors occur.
   */
  void handle(Closeable closeable) throws IOException;
}
