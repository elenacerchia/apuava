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

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wisepersist.apuava.settings.env.EnvType;
import org.wisepersist.apuava.settings.env.EnvTypeImpl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Unit tests for {@link AbstractSettingsModule} class.
 *
 * @author delight.wjk@gmail.com
 */
public class SettingsModuleTest {

  private AbstractSettingsModule settingsModule;

  /**
   * Initialises before method.
   *
   * @throws Exception If uncaught errors occur.
   */
  @BeforeMethod
  public final void setUp() throws Exception {
    settingsModule = new AbstractSettingsModule() {
      @Override
      protected void configure() {
      }
    };
  }

  /**
   * Tests {@link AbstractSettingsModule#getEnvType()} method, which should return prod environment.
   *
   * @throws Exception If uncaught errors occur.
   */
  @Test
  public final void testGetEnvType_shouldReturnProd() throws Exception {
    // Given
    System.setProperty("env", "prod");

    // When
    final EnvType envType = settingsModule.getEnvType();

    // Then
    assertNotNull(envType);
    assertEquals(envType, EnvTypeImpl.PROD);
  }

  /**
   * Tests {@link AbstractSettingsModule#getEnvType()} method.
   *
   * @throws Exception If uncaught errors occur.
   */
  @Test
  public final void testGetEnvType_shouldReturnTestIfNotExists() throws Exception {
    // Given
    System.setProperty("env", "unknown");

    // When
    final EnvType envType = settingsModule.getEnvType();

    // Then
    assertNotNull(envType);
    assertEquals(envType, EnvTypeImpl.TEST);
  }
}
