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
public class EnvTypeImplTest {

  /**
   * Test if helper method fromString works properly.
   */
  @Test
  public final void testFromString_shouldHaveMatch() {
    final Optional<EnvType> envOpt = EnvTypeImpl.fromString(EnvTypeImpl.DEV.getName());
    Assert.assertTrue(envOpt.isPresent());

    final EnvType env = envOpt.get();
    assertEquals(EnvTypeImpl.DEV, env);
  }

  /**
   * Test if helper method fromString works properly.
   */
  @Test
  public final void testFromString_shouldNotMatch() {
    final Optional<EnvType> envOpt = EnvTypeImpl.fromString("unknown");
    Assert.assertFalse(envOpt.isPresent());
  }

  /**
   * Tests {@link EnvTypeImpl#toString()} method.
   *
   * @throws Exception If uncaught errors occur.
   */
  @Test
  public final void testToString() throws Exception {
    assertEquals(EnvTypeImpl.PROD.toString(), EnvTypeImpl.PROD.getName());
  }

  /**
   * Tests {@link EnvTypeImpl#getConfigFile()} method.
   *
   * @throws Exception If uncaught errors occur.
   */
  @Test
  public final void testGetConfigFile() throws Exception {
    final String[] configFile = EnvTypeImpl.PROD.getConfigFile();
    assertNotNull(configFile);
    assertTrue(configFile.length > 0);
  }

  /**
   * Tests {@link EnvTypeImpl#valueOf(String)} method.
   *
   * @throws Exception If uncaught errors occur.
   */
  @Test
  public final void testValueOf() throws Exception {
    // Given
    final String name = EnvTypeImpl.STAGE.name();

    // When
    final EnvTypeImpl envType = EnvTypeImpl.valueOf(name);

    // Then
    assertEquals(envType, EnvTypeImpl.STAGE);
  }
}
