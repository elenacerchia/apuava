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

import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.GuiceServletContextListener;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Support class for Http integration tests.
 *
 * @author jiakuan.wang@gmail.com
 */
public abstract class AbstractHttpServerSupport {

  /**
   * Jetty port.
   */
  protected static final int JETTY_PORT = 15100;

  private static final Logger log = getLogger(AbstractHttpServerSupport.class); // NOPMD

  private Server jettyServer;
  private UrlFetcher urlFetcher = new UrlFetcher();

  /**
   * Setup Jetty environment before suite.
   *
   * @throws Exception If anything uncaught in this method.
   */
  @BeforeSuite
  public final void setUpEnvironment() throws Exception {
    startEmbeddedJetty();
  }

  /**
   * Tear down after suite.
   *
   * @throws Exception If anything uncaught in this method.
   */
  @AfterSuite
  public final void tearDownEnvironment() throws Exception {
    stopEmbeddedJetty();
  }

  /**
   * Gets the basic URL of Jetty for tests.
   *
   * @return The base URL of Jetty.
   */
  public final String getBaseUrl() {
    return "http://127.0.0.1:" + AbstractHttpServerSupport.JETTY_PORT;
  }

  /**
   * Gets the instance of {@link GuiceServletContextListener} class.
   * @return The instance of {@link GuiceServletContextListener} class.
   */
  protected abstract GuiceServletContextListener getGuiceServletContextListener();

  /**
   * Gets the message from welcome service.
   *
   * @return The message from welcome service.
   */
  protected abstract String getWelcomeServiceMessage();

  /**
   * Starts embedded Jetty for tests.
   *
   * @throws Exception If anything uncaught in this method.
   */
  private void startEmbeddedJetty() throws Exception {
    new Thread() {
      @Override
      public void run() {
        log.info("Starting embedded Jetty server on port "
                 + AbstractHttpServerSupport.JETTY_PORT);
        initJettyServer();
        startJettyServer();
      }
    }.start();
    waitUntilJettyIsReady();
  }

  /**
   * Waits until the Jetty server is ready.
   *
   * @throws InterruptedException If the thread is interrupted.
   */
  private void waitUntilJettyIsReady() throws InterruptedException {
    // Wait until the embedded jetty is ready
    boolean started = false;
    int count = 0;
    final int maxTry = 10;
    while (!started && count <= maxTry) {
      final int twoSeconds = 2000;
      Thread.sleep(twoSeconds);
      try {
        count += 1;
        final String responseContent = urlFetcher.withUrl(getBaseUrl())
            .withRequestMethod(UrlFetcher.RequestMethod.GET).execute().getContent();
        started = StringUtils.equals(getWelcomeServiceMessage(), responseContent);
      } catch (final IOException ex) {
        log.info("Waiting Jetty to be started... count=" + count);
      }
    }
    log.info("Jetty server is started successfully.");
  }

  /**
   * Initialises Jetty server after it's initialised.
   */
  @SuppressWarnings("IllegalCatch")
  private void startJettyServer() {
    try {
      jettyServer.start();
      log.info("Start complete");
      jettyServer.join();
    } catch (final Exception ex) { //NOPMD
      throw new IllegalStateException("Failed to start embedded Jetty.", ex);
    }
  }

  /**
   * Initialises Jetty server.
   */
  private void initJettyServer() {
    jettyServer = new Server(AbstractHttpServerSupport.JETTY_PORT);
    final ServletContextHandler sch = new ServletContextHandler(jettyServer, "/");
    sch.addEventListener(getGuiceServletContextListener());
    sch.addFilter(GuiceFilter.class, "/*", null);
    sch.addServlet(DefaultServlet.class, "/");
  }

  /**
   * Stops Jetty server.
   *
   * @throws Exception If anything uncaught in this method.
   */
  private void stopEmbeddedJetty() throws Exception {
    jettyServer.stop();
  }
}
