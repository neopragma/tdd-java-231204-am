package com.neopragma.fizzbuzz;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FizzbuzzTest
{
    private Fizzbuzz fizzbuzz;

    @BeforeEach
    public void beforeEachTestCase() {
        fizzbuzz = new Fizzbuzz();
    }

    @Test
    public void itReturnsFizzForTheNumber3() {
        assertEquals("Fizz", fizzbuzz.processNumber(3));
    }

}
