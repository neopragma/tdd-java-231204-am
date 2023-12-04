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

    @Test
    public void itReturnsFizzForTheNumber6() {
        assertEquals("Fizz", fizzbuzz.processNumber(6));
    }
    @Test
    public void itReturnsBuzzForTheNumber5() {
        assertEquals("Buzz", fizzbuzz.processNumber(5));
    }
    @Test
    public void itReturnsBuzzForTheNumber10() {
        assertEquals("Buzz", fizzbuzz.processNumber(10));
    }
    @Test
    public void itReturnsFizzBuzzForTheNumber15() {
        assertEquals("FizzBuzz", fizzbuzz.processNumber(15));
    }
    @Test
    public void itReturnsFizzBuzzForTheNumber30() {
        assertEquals("FizzBuzz", fizzbuzz.processNumber(30));
    }
    @Test
    public void itReturns2ForTheNumber2() {
        assertEquals("2", fizzbuzz.processNumber(2));
    }

}
