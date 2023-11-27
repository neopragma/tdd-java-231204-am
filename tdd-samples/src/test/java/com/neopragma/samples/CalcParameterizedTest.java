package com.neopragma.samples;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test cases for the sum() method of class Calc
 * using a JUnit Parameterized Test (also known as a Data-Driven Test).
 */
public class CalcParameterizedTest {

    private Calc calc;

    @BeforeEach
    public void initialize_code_under_test() {
        calc = new Calc();
    }

    @ParameterizedTest
    @MethodSource("provideValuesForSum")
    void itSumsValues(Double[] input, Double expected) {
        assertEquals(expected, calc.sum(input));
    }

    private static Stream<Arguments> provideValuesForSum() {
        return Stream.of(
                Arguments.of(new Double[] { 1.5, 2.0 }, 2.5),
                Arguments.of(new Double[] { 1.5, 2.0, 3.2 }, 6.7)
        );
    }
}

