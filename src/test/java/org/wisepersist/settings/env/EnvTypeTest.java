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

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Test for EnvType class.
 *
 * @author jiakuan.wang@gmail.com
 */
public class EnvTypeTest {

  /**
   * Test if helper method fromString works properly.
   */
  @Test
  public final void testFromString_shouldHaveMatch() {
    final Optional<Env> envOpt = EnvType.fromString(EnvType.DEV.getName());
    Assert.assertTrue(envOpt.isPresent());

    final Env env = envOpt.get();
    assertEquals(EnvType.DEV, env);
  }

  /**
   * Test if helper method fromString works properly.
   */
  @Test
  public final void testFromString_shouldNotMatch() {
    final Optional<Env> envOpt = EnvType.fromString("unknown");
    Assert.assertFalse(envOpt.isPresent());
  }

  /**
   * Tests {@link EnvType#toString()} method.
   *
   * @throws Exception If uncaught errors occur.
   */
  @Test
  public final void testToString() throws Exception {
    assertEquals(EnvType.PROD.toString(), EnvType.PROD.getName());
  }

  /**
   * Tests {@link EnvType#getConfigFile()} method.
   *
   * @throws Exception If uncaught errors occur.
   */
  @Test
  public final void testGetConfigFile() throws Exception {
    final String[] configFile = EnvType.PROD.getConfigFile();
    assertNotNull(configFile);
    assertTrue(configFile.length > 0);
  }

  /**
   * Tests {@link EnvType#valueOf(String)} method.
   *
   * @throws Exception If uncaught errors occur.
   */
  @Test
  public final void testValueOf() throws Exception {
    // Given
    final String name = EnvType.STAGE.name();

    // When
    final EnvType envType = EnvType.valueOf(name);

    // Then
    assertEquals(envType, EnvType.STAGE);
  }
}
