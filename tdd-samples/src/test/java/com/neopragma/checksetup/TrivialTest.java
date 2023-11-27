package com.neopragma.checksetup;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * See if our tools are configured properly to run JUnit.
 * The sample test case should fail because the expected and actual values differ.
 * We should not see any issues accessing JUnit packages.
 */
public class TrivialTest {
    @Test
    public void is_junit_set_up() {
        TrivialClass trivialObj = new TrivialClass();
        assertEquals("This is not trivial.", trivialObj.isThisTrivial());
    }
}
