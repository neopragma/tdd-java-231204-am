package com.neopragma.samples;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test cases for the sum() method of class Calc
 * using the JUnit test library - basic implementation.
 */
public class CalcTest {
    @Test
    public void it_sums_two_values() {
        Calc calc = new Calc();
        assertEquals(2.5, calc.sum(1.5,2.0));
    }
    @Test
    public void it_sums_three_values() {
        Calc calc = new Calc();
        assertEquals(6.7, calc.sum(1.5,2.0,3.2));
    }
}

