package com.neopragma.rpn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RPNTest {
    private RPN rpn;

    @BeforeEach
    public void beforeEachTest() {
        rpn = new RPN();
    }
    @ParameterizedTest
    @MethodSource("provideValuesForSingleNumberEntryTests")
    public void it_stores_the_number_entered(Double value) {
        assertEquals(value, rpn.enter(value));
    }

    private static Stream<Arguments> provideValuesForSingleNumberEntryTests() {
        return Stream.of(
                Arguments.of(1.0),
                Arguments.of(2.0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideValuesForSimpleOperations")
    public void it_performs_simple_operations(
            Double expected, Double value1, Double value2, String operator
    ) {
        rpn.enter(value1);
        rpn.enter(value2);
        assertEquals(expected, rpn.enterOperator(operator));
    }
    private static Stream<Arguments> provideValuesForSimpleOperations() {
        return Stream.of(
                Arguments.of(7.0, 3.0, 4.0, "+"),
                Arguments.of(3.5, 5.0, 8.5, "-"),
                Arguments.of(63.0, 7.0, 9.0, "*"),
                Arguments.of(5.0, 3.0, 15.0, "/")
        );
    }

    @Test
    public void it_handles_a_slightly_more_complicated_expression() {
        rpn.enter(3.0);
        rpn.enter(6.0);
        rpn.enterOperator("+");
        rpn.enter(3.0);
        assertEquals(27.0, rpn.enterOperator("*"));
    }

}
