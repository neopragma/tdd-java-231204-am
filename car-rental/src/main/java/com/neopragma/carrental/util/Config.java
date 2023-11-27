package com.neopragma.carrental.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.neopragma.carrental.Constants.DEFAULT_CONFIG_PATHNAME;
import static com.neopragma.carrental.Constants.EMPTY_STRING;

/**
 * Convenience methods for managing application properties
 */
public class Config {
    private static Properties props = null;

    /**
     * Initialize configuration settings from default .properties file.
     */
    public static void load() {
        loadFromFile(DEFAULT_CONFIG_PATHNAME);
    }

    /**
     * Initialize configuration settings
     * @param pathname - Java .properties file containing configuration values.
     *        If filename is null or an empty string, use default filename.
     */
    public static void loadFromFile(String pathname) {
        props = new Properties();
        if (null == pathname || pathname.equals(EMPTY_STRING)) {
            pathname = DEFAULT_CONFIG_PATHNAME;
        }
        try (InputStream is = Config.class.getResourceAsStream(pathname)) {
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Replace entire properties object
     * @param inputProps - set of properties to set
     */
    public static void loadFromObject(Properties inputProps) {
        props = inputProps;
    }

    /**
     * @param key
     * @return the value corresponding to the specified key, or the empty string if the key is not found.
     */
    public static String valueOf(String key) {
        return valueOf(key, EMPTY_STRING);
    }

    /**
     * @param key
     * @param defaultValue
     * @return the value corresponding to the specified key, or the default value if the key is not found.
     */
    public static String valueOf(String key, String defaultValue) {
        if (Strings.isEmpty(key)) {
            throw new RuntimeException("Config.valueOf() was called with null or empty key.");
        }
        String value = props.getProperty(key);
        return value == null ? defaultValue : value;
    }
}
