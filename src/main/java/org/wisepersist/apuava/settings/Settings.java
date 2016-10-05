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

package org.wisepersist.apuava.settings;

/**
 * Interface for environment configuration getter.
 *
 * @author jiakuan.wang@gmail.com
 */
public interface Settings {

  /**
   * Get config setting.
   * @param key Is the key name.
   * @return Int config. 0 if not found.
   */
  Integer getInt(String key);

  /**
   * Get config setting.
   * @param key Is the key name.
   * @param defaultVal The default value.
   * @return Int config. With default.
   */
  Integer getInt(String key, int defaultVal);

  /**
   * Get config setting.
   * @param key Is the key name.
   * @return Boolean config. false if not found.
   */
  Boolean getBoolean(String key);

  /**
   * Get config setting.
   * @param key Is the key name.
   * @return Boolean config. false if not found.
   */
  Double getDouble(String key);

  /**
   * Get config setting.
   * @param key Is the key name.
   * @param defaultVal The default value.
   * @return Boolean config. With default.
   */
  Double getDouble(String key, double defaultVal);

  /**
   * Gets a setting value by given setting name. If no value found, gets the default
   * value provided.
   *
   * @param key The setting name specified.
   * @param defaultVal The default value.
   * @return The setting value if exists, otherwise default value.
   */
  String getString(final String key, final String defaultVal);

  /**
   * Gets a setting value by given setting name. If no value found, empty string will be returned.
   *
   * @param name The setting name specified.
   * @return The setting value if exists, otherwise empty string.
   */
  String getString(final String name);

}
