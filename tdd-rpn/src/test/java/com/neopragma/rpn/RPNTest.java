package com.neopragma.rpn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RPNTest
{
    private RPN rpn;

    @BeforeEach
    public void beforeEachTestCase() {
        rpn = new RPN();
    }

    @Test
    public void entering_a_number_adds_the_number_to_the_stack() {
        assertEquals("3.75", rpn.enter("3.75"));
    }
}
