package com.neopragma.samples;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test cases for the sum() method of class Calc
 * using the JUnit test library - refactored to reduce duplication
 * and provide an example of the @BeforeEach annotation.
 */
public class CalcRefactoredTest {

    private Calc calc;

    @BeforeEach
    public void initialize_code_under_test() {
        calc = new Calc();
    }

    @Test
    public void it_sums_two_values() {
        assertEquals(2.5, calc.sum(1.5,2.0));
    }
    @Test
    public void it_sums_three_values() {
        assertEquals(6.7, calc.sum(1.5,2.0,3.2));
    }
}

