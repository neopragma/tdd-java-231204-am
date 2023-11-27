package com.neopragma.mocks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoredIntegrationTest {

    @Test
    public void play() throws Exception {
        Bored sut = new Bored();
        assertEquals("Suggested activity", sut.whatShouldIDo());
    }
}
