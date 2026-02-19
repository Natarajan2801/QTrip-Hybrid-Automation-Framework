package com.qtrip.exceptions;

/**
 * Custom exception for configuration-related errors.
 * Thrown when required properties are missing or invalid.
 *
 * @author Natarajan M
 */
public class ConfigurationException extends FrameworkException {

    public ConfigurationException(String propertyName) {
        super("Configuration property not found: " + propertyName);
    }

    public ConfigurationException(String propertyName, String message) {
        super(String.format("Configuration error for '%s': %s", propertyName, message));
    }
}

