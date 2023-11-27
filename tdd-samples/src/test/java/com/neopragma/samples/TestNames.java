package com.neopragma.samples;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class provides some examples of common naming conventions for examples.
 * Several naming conventions are popular. The important thing is that your team and/or
 * organization uses a consistent convention.
 * The names of examples are typically longer than the names of production methods.
 * For that reason, people often separate parts of the method names with underscores
 * for readability. Some people routinely use snake case and avoid camel case for
 * test method names.
 */
public class TestNames {

    /**
     * Example 1. Name does not express a concrete example of adding two integers.
     * This kind of name is not recommended. The purpose of the test case is unclear.
     */
    @Test
    public void testAddingTwoIntegers() {
        assertEquals(12, addIntegers(5,7));
    }

    /**
     * Example 2. Improvement.
     * Name expresses a concrete example of adding two integers.
     */
    @Test
    public void testAddingFiveAndSeven() {
        assertEquals(12, addIntegers(5,7));
    }

    /**
     * Example 3. Improvement.
     * Unnecessary word "test" is removed. We know all methods annotated @Test are examples.
     */
    @Test
    public void addingFiveAndSevenShouldResultInTwelve() {
        assertEquals(12, addIntegers(5,7));
    }

    /**
     * Example 4. Improvement.
     * Visually separate the action, inputs, and result for readability.
     */
    @Test
    public void adding_FiveAndSeven_Should_ResultInTwelve() {
        assertEquals(12, addIntegers(5,7));
    }

    /**
     * Example 5. Improvement (subjective).
     * Re-ordering the phrases improves readability (for some people).
     */
    @Test
    public void shouldReturn_12_when_adding_5_and_7() {
        assertEquals(12, addIntegers(5,7));
    }

    /**
     * Example 6. Improvement (subjective).
     * Assert the SUT *does* something rather than it *should* do something.
     */
    @Test
    public void it_returns_12_when_adding_5_and_7() {
        assertEquals(12, addIntegers(5,7));
    }

    /**
     * Example 7. Improvement (subjective).
     * State the functionality being checked.
     */
    @Test
    public void addIntegers_returns_12_when_adding_5_and_7() {
        assertEquals(12, addIntegers(5,7));
    }

    /**
     * Example 8. Same style as Example 7 but for a different domain.
     */
    @Test
    public void transferFunds_returns_ContactProviderMessage_when_accountHasFraudHold() {
        // set up preconditions
        // execute SUT
        // assertEquals(contactProviderMessage, transferFunds(sourceAccount, destAccount));
    }

    /**
     * Example 9. Improvement (subjective).
     * Express the preconditions first to make it easy to distinguish examples when reading
     * a list of long method names. Otherwise, the first part of the method names will be
     * the same for several or many examples.
     */
    @Test
    public void when_accountHasFraudHold_then_transferFunds_returns_ContactProviderMessage() {
        // set up preconditions
        // execute SUT
        // assertEquals(contactProviderMessage, transferFunds(sourceAccount, destAccount));
    }

    /**
     * Example 10. Given-When-Then style.
     * A style inspired by BDD. Possibly more readable but also possibly more verbose.
     */
    @Test
    public void given_accountHasFraudHold_when_transferFunds_isCalled_then_it_returns_ContactProviderMessage() {
        // set up preconditions
        // execute SUT
        // assertEquals(contactProviderMessage, transferFunds(sourceAccount, destAccount));
    }

    private int addIntegers(int val1, int val2) {
        return val1 + val2;
    }


}
