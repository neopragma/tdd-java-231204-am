package com.neopragma.carrental.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Locale;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class I18nTest {

    @Test
    public void it_returns_text_for_en_US() {
        I18n.setDefaultLocale();
        assertEquals("Rental Agreement", I18n.textOf("text.rental.contract"));
    }

    @ParameterizedTest
    @MethodSource("providerForI18nChecks")
    public void it_returns_text_for_fr_FR() {
        I18n.setLocale("fr", "FR");
        Locale l = I18n.currentLocale();
        assertEquals("Contrat de location", I18n.textOf("text.rental.contract"));
    }

    private static Stream<Arguments> providerForI18nChecks() {
        return Stream.of(
                Arguments.of("fr", "FR", "text.rental.contract", "Contrat de location"),
                Arguments.of("de", "DE", "text.rental.contract", "Mietvertrag"),
                Arguments.of("fr", "FR", "text.rental.contract", "Contrato de arrendamiento")
        );
    }

    @ParameterizedTest
    @MethodSource("provideArgsForSetLocale")
    public void it_throws_when_setLocale_is_called_with_null_or_empty_arguments(
            String language, String country, String variant) {
        assertThrows(RuntimeException.class, () -> {
            I18n.setLocale(language, country, variant);
        });
    }

    private static Stream<Arguments> provideArgsForSetLocale() {
        return Stream.of(
                Arguments.of((Object[]) new String[] {"ja", "JA", null}),
                Arguments.of((Object[]) new String[] { " ", "DE", "Bayern"}),
                Arguments.of((Object[]) new String[] { "fr", "   \n", "CA"}),
                Arguments.of((Object[]) new String[] { "\t", "", null})
        );
    }

}
