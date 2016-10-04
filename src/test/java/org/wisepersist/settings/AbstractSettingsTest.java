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

package org.wisepersist.settings;

import org.testng.annotations.Test;
import org.wisepersist.settings.env.EnvType;
import org.wisepersist.settings.env.EnvTypeImpl;

import static org.testng.Assert.assertNotNull;

/**
 * Unit tests for {@link AbstractSettings} class.
 *
 * @author jiakuan.wang@gmail.com
 */
@SuppressWarnings("AbstractClassName")
public class AbstractSettingsTest {

  /**
   * Tests {@link AbstractSettings#initialize(String[])}.
   */
  @Test
  public final void testInitialize() {
    // Given and When
    final AbstractSettings settings1 = new FileBasedAppConfig("/test/test-settings.properties");
    settings1.ensureConfigNotNull();
    final AbstractSettings settings2 = new FileBasedAppConfig("/test/test-settings.config");
    settings2.ensureConfigNotNull();
    final AbstractSettings settings3 = new FileBasedAppConfig("/test/freemarker_test.html");
    settings3.ensureConfigNotNull();
    final AbstractSettings settings4 = new FileBasedAppConfig(EnvTypeImpl.TEST);

    // Then
    assertNotNull(settings1);
    assertNotNull(settings2);
    assertNotNull(settings3);
    assertNotNull(settings4);
  }

  /**
   * Tests {@link AbstractSettings#initialize(String[])}.
   */
  @Test(expectedExceptions = IllegalStateException.class)
  public final void testInitialize_invalidXmlFormat() {
    new FileBasedAppConfig("/test/test-settings-invalid.xml");
  }

  /**
   * Tests {@link AbstractSettings#initialize(String[])}.
   */
  @Test(expectedExceptions = IllegalStateException.class)
  public final void testInitialize_invalidFile() {
    new FileBasedAppConfig("/test/unknownFile.txt");
  }

  /**
   * Test impl class for {@link AbstractSettings}.
   *
   * @author jiakuan.wang@gmail.com
   */
  private static final class FileBasedAppConfig extends AbstractSettings {

    /**
     * Constructor for file base property.
     *
     * @param fileName The name of file to be loaded.
     */
    private FileBasedAppConfig(final String fileName) {
      super(fileName);
    }

    /**
     * Constructor for file base property.
     *
     * @param env The env that contains the path of file to be loaded.
     */
    private FileBasedAppConfig(final EnvType env) {
      super(env);
    }

    @Override
    protected Class getClassType() {
      return getClass();
    }

    @Override
    protected String getAppConfigFolder() {
      return "";
    }
  }
}
