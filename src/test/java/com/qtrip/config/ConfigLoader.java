package com.qtrip.config;

import com.qtrip.constants.FrameworkConstants;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Objects;

public final class ConfigLoader {
    private ConfigLoader() {}
    private static Properties properties = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream(FrameworkConstants.CONFIG_FILE_PATH)) {
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (Objects.isNull(value)) throw new RuntimeException("Property " + key + " not found");
        return value;
    }
}