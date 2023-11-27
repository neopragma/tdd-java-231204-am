package com.neopragma.mock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class CatFactsIntegrationTest {

    @Test
    public void play() throws Exception {
        FormatCatFact sut = new FormatCatFact();
        assertTrue(sut.formattedCatFact(new CatFacts()).startsWith("\"{\\\"fact\\\":"));
    }
}
