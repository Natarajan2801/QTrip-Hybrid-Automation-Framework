package com.qtrip.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Configuration Manager for loading application properties.
 * Reads from config.properties file.
 *
 * @author Natarajan M
 */
public final class EnvironmentManager {

    private static final String CONFIG_PATH = "src/test/resources/config.properties";
    private static Properties properties;

    private EnvironmentManager() {}

    /**
     * Initialize and load configuration.
     */
    private static void loadConfig() {
        if (properties == null) {
            properties = new Properties();
            try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
                properties.load(fis);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load config.properties", e);
            }
        }
    }

    /**
     * Get property value (required).
     */
    public static String get(String key) {
        loadConfig();
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in config.properties");
        }
        return value;
    }

    /**
     * Get property with default value.
     */
    public static String get(String key, String defaultValue) {
        loadConfig();
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Get property as integer.
     */
    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    /**
     * Get property as integer with default.
     */
    public static int getInt(String key, int defaultValue) {
        try {
            return Integer.parseInt(get(key, String.valueOf(defaultValue)));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Get property as boolean.
     */
    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }

    /**
     * Get property as boolean with default.
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        return Boolean.parseBoolean(get(key, String.valueOf(defaultValue)));
    }
}

