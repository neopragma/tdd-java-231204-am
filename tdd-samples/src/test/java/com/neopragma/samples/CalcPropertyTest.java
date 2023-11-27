package com.neopragma.samples;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Property test example for the sum() method of class Calc.
 */
public class CalcPropertyTest {

    @Property(tries=100)
    void orderOfArgumentsDoesNotAffectResult(
            @ForAll Double val1, @ForAll Double val2, @ForAll Double val3) {
        Calc calc = new Calc();
        Double res1 = calc.sum(val1, val2, val3);
        Double res2 = calc.sum(val3, val1, val2);
        assertEquals(res1, res2, 0.1);
    }
}

