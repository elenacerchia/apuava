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

import com.google.common.base.Optional;

import java.util.Arrays;

/**
 * Has list of supported environments and list of configs.
 *
 * @author jiakuan.wang@gmail.com
 */
public enum EnvTypeImpl implements EnvType {

  /**
   * Production environment.
   */
  PROD("prod", "/prod/config.xml"),

  /**
   * Staging environment.
   */
  STAGE("stage", "/stage/config.xml"),

  /**
   * Development environment.
   */
  DEV("dev", "/dev/config.xml"),

  /**
   * Testing environment.
   */
  TEST("test", "/test/config.xml");

  private final String name;
  private final String[] configFile;

  /**
   * Constructor with param.
   *
   * @param name Is name of the env.
   * @param configFile Is the config file location.
   */
  EnvTypeImpl(final String name, final String... configFile) {
    this.name = name;
    this.configFile = configFile;
  }

  @Override
  public String toString() {
    return getName();
  }

  public String getName() {
    return name;
  }

  public String[] getConfigFile() {
    return Arrays.copyOf(configFile, configFile.length);
  }

  /**
   * Checks match environment from string.
   *
   * @param value The value to be evaluated.
   * @return The {@link EnvType} instance matched, otherwise return null if not found.
   */
  public static Optional<EnvType> fromString(final String value) { //NOPMD
    Optional<EnvType> matchEnv = Optional.absent();
    for (final EnvType env : EnvTypeImpl.values()) {
      if (env.getName().equalsIgnoreCase(value)) {
        matchEnv = Optional.of(env);
        break;
      }
    }
    return matchEnv;
  }
}
