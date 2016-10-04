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

package org.wisepersist.settings.guice;

import com.google.inject.Module;

import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertNotNull;

/**
 * Unit tests for {@link ModuleBuilder} class.
 *
 * @author jiakuan.wang@gmail.com
 */
public class ModuleBuilderTest {

  /**
   * Tests {@link ModuleBuilder#build(Module...)} method.
   *
   * @throws Exception If uncaught errors occur.
   */
  @Test
  public final void testBuild() throws Exception {
    // Given
    final ModuleBuilder builder = new ModuleBuilder();

    // When
    final Module module = builder.build(
        mock(Module.class), mock(Module.class), mock(Module.class), mock(Module.class));

    // Then
    assertNotNull(module);
  }
}