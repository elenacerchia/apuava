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

package org.wisepersist.apuava.urlfetch;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Guice configuration for unit tests.
 *
 * @author jiakuan.wang@gmail.com
 */
public class TestAppGuiceConfig extends GuiceServletContextListener {

  private static final Logger log = LoggerFactory.getLogger(TestAppGuiceConfig.class); //NOPMD

  @SuppressWarnings("IllegalCatch")
  @Override
  public final Injector getInjector() {
    try {
      return Guice.createInjector(getServletModule());
    } catch (final Exception ex) { //NOPMD
      log.error("Failed to create injector: " + ex.getMessage(), ex);
      throw ex;
    }
  }

  /**
   * Initialises SiteBricks Guice module.
   *
   * @return The SiteBricks Guice module initialised.
   */
  private ServletModule getServletModule() {
    return new ServletModule() {
      @Override
      protected void configureServlets() {
        serve("/").with(TestWelcomeServlet.class);
        serve("/testget").with(TestGetServlet.class);
        serve("/testpost").with(TestPostServlet.class);
      }
    };
  }
}
