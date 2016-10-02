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

package org.wisepersist.settings.env;

/**
 * This interface defines the type class for environment data information.
 *
 * @author jiakuan.wang@gmail.com
 */
public interface Env {

  /**
   * Respond with the name of the environment.
   * @return Name of environment.
   */
  String getName();

  /**
   * Respond with the configuration files resource paths.
   * @return Array of file resource paths.
   */
  String[] getConfigFile();

}
