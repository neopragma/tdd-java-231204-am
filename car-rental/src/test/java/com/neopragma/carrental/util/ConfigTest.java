package com.neopragma.carrental.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConfigTest {

    private Properties props;

    @BeforeEach
    public void common_setup() {
        props = new Properties();
    }

    @Test
    public void it_returns_the_value_corresponding_to_the_specified_key() {
        props.put("testkey", "testvalue");
        Config.loadFromObject(props);
        assertEquals("testvalue", Config.valueOf("testkey"));
    }

    @Test
    public void it_returns_the_default_value_when_the_key_is_not_found() {
        Config.loadFromObject(props);
        assertEquals("defaultvalue", Config.valueOf("testkey", "defaultvalue"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    public void it_throws_when_the_specified_key_is_invalid(String key) {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Config.loadFromObject(props);
            Config.valueOf(key);
        });
        assertEquals("Config.valueOf() was called with null or empty key.", exception.getMessage());
    }

}
