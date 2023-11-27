package com.neopragma.samples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Working with code that can throw exceptions
 */
public class Exception1Test {

    /**
     * This throws java.lang.ArithmeticException
     */
    @Test
    public void divide_by_zero() {
        Exception1 sut = new Exception1();
        assertEquals(0.0, sut.divide(5, 0));
    }

    /**
     * How to assert the correct exception was thrown and the expected
     * exception message was emitted.
     */
    @Test
    public void assert_expected_exception() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Exception1 sut = new Exception1();
            sut.divide(5, 0);
        });
        assertEquals("/ by zero", exception.getMessage());
    }

    /**
     * You may see this approach in older code.
     */
    @Test
    public void assert_expected_exception_old_way() {
        try {
            Exception1 sut = new Exception1();
            sut.divide(5, 0);
            fail("Expected ArithmeticException but no exception was thrown.");
        } catch (ArithmeticException expectedException) {
            assertEquals("/ by zero", expectedException.getMessage());
        } catch (Exception ex) {
            fail("Unexpected Exception " + ex + " was thrown.");
        }
    }
}

