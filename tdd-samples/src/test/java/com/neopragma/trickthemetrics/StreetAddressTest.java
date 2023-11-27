package com.neopragma.trickthemetrics;

import com.neopragma.samples.Calc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Meaningless examples that produce 100% test coverage.
 */
public class StreetAddressTest {

    private String testAddress = "123 Mockingbird Lane, St.Louis, MO 12345";
    private StreetAddress sut;

    @BeforeEach
    public void commonSetup() {
        sut = new StreetAddress(testAddress);
    }
    @Test
    public void constructor_returnsValidInstance() {
        assertNotNull(sut);
    }

    @Test
    public void toString_returnsRawValue() {
        String result = sut.toString();
    }

    @Test
    public void format_returnsFormattedUSAddress() {
        String result = sut.format(new Locale("en_US"));
    }

}

