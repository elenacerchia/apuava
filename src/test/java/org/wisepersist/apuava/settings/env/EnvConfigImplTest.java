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

package org.wisepersist.apuava.settings.env;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Unit tests for {@link EnvConfigImpl} class.
 *
 * @author delight.wjk@gmail.com
 */
public class EnvConfigImplTest {

  /**
   * Test if helper methods respond properly.
   */
  @Test
  public final void testIsProd_shouldOnlyTrue() {
    final EnvType expectedEnv = EnvTypeImpl.PROD;
    final EnvConfig envConfig = new EnvConfigImpl(expectedEnv);

    assertEquals(envConfig.getEnv(), expectedEnv);
    assertTrue(envConfig.isProd());
    assertFalse(envConfig.isStage());
    assertFalse(envConfig.isDev());
    assertFalse(envConfig.isTest());
  }

  /**
   * Test if helper methods respond properly.
   */
  @Test
  public final void testIsStage_shouldOnlyTrue() {
    final EnvType expectedEnv = EnvTypeImpl.STAGE;
    final EnvConfig envConfig = new EnvConfigImpl(expectedEnv);

    assertEquals(envConfig.getEnv(), expectedEnv);
    assertFalse(envConfig.isProd());
    assertTrue(envConfig.isStage());
    assertFalse(envConfig.isDev());
    assertFalse(envConfig.isTest());
  }

  /**
   * Test if helper methods respond properly.
   */
  @Test
  public final void testIsDev_shouldOnlyTrue() {
    final EnvType expectedEnv = EnvTypeImpl.DEV;
    final EnvConfig envConfig = new EnvConfigImpl(expectedEnv);

    assertEquals(envConfig.getEnv(), expectedEnv);
    assertFalse(envConfig.isProd());
    assertFalse(envConfig.isStage());
    assertTrue(envConfig.isDev());
    assertFalse(envConfig.isTest());
  }

  /**
   * Test if helper methods respond properly.
   */
  @Test
  public final void testIsTest_shouldOnlyTrue() {
    final EnvType expectedEnv = EnvTypeImpl.TEST;
    final EnvConfig envConfig = new EnvConfigImpl(expectedEnv);

    assertEquals(envConfig.getEnv(), expectedEnv);
    assertFalse(envConfig.isProd());
    assertFalse(envConfig.isStage());
    assertFalse(envConfig.isDev());
    assertTrue(envConfig.isTest());
  }
}
