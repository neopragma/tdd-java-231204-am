package com.neopragma.carrental.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class StringsTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    public void it_recognizes_logically_empty_strings(String value) {
        assertTrue(Strings.isEmpty(value));
    }

    @Test
    public void it_recognizes_a_string_that_is_not_logically_empty() {
        assertTrue(Strings.isNotEmpty("alpha"));
    }

    @Test
    public void it_does_not_throw_when_all_string_args_are_valid() {
        Strings.assertValidStringArgs("a", "b", "c", "d");
    }

    @ParameterizedTest
    @MethodSource("provideStringArgLists")
    public void it_throws_when_a_string_arg_is_invalid(String[] args) {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Strings.assertValidStringArgs(args);
        });
        assertEquals("Invalid String argument value.", exception.getMessage());
    }
    private static Stream<Arguments> provideStringArgLists() {
        return Stream.of(
                Arguments.of(new String[] { "valid1", "valid2", null},
                             new String[] { " ", "valid2", "valid3"},
                             new String[] { "valid1", "   \n", "valid3"},
                             new String[] { "\t", "", null})
        );
    }

}
