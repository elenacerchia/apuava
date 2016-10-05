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

import com.google.common.base.Optional;
import com.google.inject.AbstractModule;

import org.wisepersist.apuava.settings.env.EnvType;
import org.wisepersist.apuava.settings.env.EnvTypeImpl;

/**
 * Abstract settings module which supports env identification.
 *
 * @author jiakuan.wang@gmail.com
 */
public abstract class AbstractSettingsModule extends AbstractModule {

  /**
   * To get the environment type, default is test.
   * @return EnvironmentType base on config.
   */
  protected final EnvType getEnvType() {
    EnvType env = EnvTypeImpl.TEST;
    final String configuredEnv = System.getProperty("env", env.getName());
    final Optional<EnvType> envTypeOpt = EnvTypeImpl.fromString(configuredEnv);
    if (envTypeOpt.isPresent()) {
      env = envTypeOpt.get();
    }
    return env;
  }

}
