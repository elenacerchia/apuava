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

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Implementation of {@link EnvConfig} interface.
 *
 * @author jiakuan.wang@gmail.com
 */
@Singleton
public class EnvConfigImpl implements EnvConfig {

  private final EnvType env;

  /**
   * Constructor for EnvironmentInfoService object.
   * @param env Is the environment type.
   */
  @Inject
  public EnvConfigImpl(final EnvType env) {
    this.env = env;
  }

  /**
   * To get the environment type set on object creation.
   * @return EnvironmentType of environment info.
   */
  public final EnvType getEnv() {
    return env;
  }

  /**
   * True is on production, otherwise false.
   * @return Boolean value of evaluation.
   */
  public final boolean isProd() {
    return EnvTypeImpl.PROD.equals(env);
  }

  /**
   * True is on stage, otherwise false.
   * @return Boolean value of evaluation.
   */
  public final boolean isStage() {
    return EnvTypeImpl.STAGE.equals(env);
  }

  /**
   * True is on dev, otherwise false.
   * @return Boolean value of evaluation.
   */
  public final boolean isDev() {
    return EnvTypeImpl.DEV.equals(env);
  }

  /**
   * True is on test, otherwise false.
   * @return Boolean value of evaluation.
   */
  public final boolean isTest() {
    return EnvTypeImpl.TEST.equals(env);
  }

}
