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

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wisepersist.apuava.resource.ResourceHandler;
import org.wisepersist.apuava.resource.ResourceManager;
import org.wisepersist.apuava.settings.env.EnvType;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * The abstract class for most used config getter methods.
 *
 * @author jiakuan.wang@gmail.com
 */
public abstract class AbstractSettings implements Settings { //NOPMD

  private static final Logger log = LoggerFactory.getLogger(AbstractSettings.class); //NOPMD

  private ResourceManager resourceManager = new ResourceManager();
  private Properties mergedProperties;

  /**
   * Constructor by EnvType.
   *
   * @param env The env of this settings.
   */
  public AbstractSettings(final EnvType env) {
    this(env.getConfigFile());
  }

  /**
   * Constructor by config paths.
   *
   * @param paths The env of this settings.
   */
  public AbstractSettings(final String... paths) {
    initialize(paths);
  }

  /**
   * Initialize config prop.
   * @param paths Is the resource location of config.
   */
  private void initialize(final String... paths) {
    ensureConfigNotNull();
    for (final String path : paths) {
      readConfigFile(path);
    }
  }

  /**
   * Ensures that the {@link #mergedProperties} is not null.
   */
  protected final void ensureConfigNotNull() {
    if (mergedProperties == null) {
      mergedProperties = new Properties();
    }
  }

  /**
   * Reads the config file and add them to main props data structure.
   * @param path Is the path to resource config file.
   */
  private void readConfigFile(final String path) {
    final String filePath = composeResourceFilePath(path);
    try {
      final InputStream inputStream = getClassType().getResourceAsStream(filePath);
      resourceManager.with(inputStream).run(new ResourceHandler<Void>() {
        @Override
        public Void handle(final Closeable closeable) throws IOException {
          ensureInputStreamIsValid(inputStream);
          mergedProperties.putAll(loadProperties(path, inputStream));
          return null;
        }
      });
    } catch (final IOException ex) {
      log.error(ex.getMessage(), ex);
      throw new IllegalStateException("Couldn't process resource file.", ex);
    }
  }

  /**
   * Loads properties from a path.
   *
   * @param path The path specified.
   * @param inputStream The input stream of the path specified.
   * @return Properties read from the path.
   * @throws IOException If IO errors occur.
   */
  private Properties loadProperties(final String path, final InputStream inputStream)
      throws IOException {
    final Properties configFile = new Properties();
    if (path.endsWith(".xml")) {
      configFile.loadFromXML(inputStream);
    } else if (path.endsWith(".properties") || path.endsWith(".config")) {
      configFile.load(inputStream);
    }
    return configFile;
  }

  /**
   * Checks if the specified {@link InputStream} inputStream valid.
   *
   * @param inputStream The specified {@link InputStream}.
   * @throws IOException If stream inputStream null.
   */
  private void ensureInputStreamIsValid(final InputStream inputStream) throws IOException {
    if (inputStream == null) {
      throw new IOException("Invalid file.");
    }
  }

  /**
   * Method to compose filePath from path provided.
   * @param path The config file path provided on resource.
   * @return The composed resource file path.
   */
  private String composeResourceFilePath(final String path) {
    String filePath = getAppConfigFolder();
    if (isEmpty(filePath)) {
      filePath = path;
    } else {
      filePath = "/".concat(filePath).concat(path);
    }
    return filePath;
  }

  /**
   * Gets a setting value by given setting name. If no value found, gets the default
   * value provided.
   *
   * @param name The setting name specified.
   * @param defaultVal The default value.
   * @return The setting value if exists, otherwise default value.
   */
  protected final String get(final String name, final String defaultVal) {
    String value = System.getProperty(name);
    if (StringUtils.isBlank(value)) {
      value = mergedProperties.getProperty(name, defaultVal);
    } else {
      log.info("get {} from system property instead of system config file", name);
    }
    return value;
  }

  /**
   * Gets a setting value by given setting name. If no value found, empty string will be returned.
   *
   * @param name The setting name specified.
   * @return The setting value if exists, otherwise empty string.
   */
  protected final String get(final String name) {
    return get(name, "").trim();
  }

  /**
   * Gets the type of the concrete settings class.
   * @return The type of the concrete settings class.
   */
  protected abstract Class getClassType();

  /**
   * Gets the settings folder name on resource.
   * @return The settings folder name.
   */
  protected abstract String getAppConfigFolder();

  @Override
  public final String getString(final String key) {
    return get(key);
  }

  @Override
  public final String getString(final String key, final String defaultVal) {
    return get(key, defaultVal);
  }

  @Override
  public final Integer getInt(final String key) {
    return getInt(key, 0);
  }

  @Override
  public final Integer getInt(final String key, final int defaultVal) {
    final String value = getString(key);
    int valueInt = defaultVal;
    if (isNotEmpty(value)) {
      valueInt = NumberUtils.toInt(value, defaultVal);
    }
    return valueInt;
  }

  @Override
  public final Boolean getBoolean(final String key) {
    final String value = getString(key);
    boolean valueBoolean = false;
    if (isNotEmpty(value)) {
      valueBoolean = BooleanUtils.toBoolean(value);
    }
    return valueBoolean;
  }

  @Override
  public final Double getDouble(final String key) {
    return getDouble(key, 0D);
  }

  @Override
  public final Double getDouble(final String key, final double defaultVal) {
    final String value = getString(key);
    double valueDouble = defaultVal;
    if (isNotEmpty(value)) {
      valueDouble = NumberUtils.toDouble(value, defaultVal);
    }
    return valueDouble;
  }

}
