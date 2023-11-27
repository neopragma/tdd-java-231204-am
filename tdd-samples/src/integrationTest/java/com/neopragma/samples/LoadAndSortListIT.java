package com.neopragma.samples;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Sample integration test
 */
public class LoadAndSortListIT {

    @Test
    public void itReturnsTheInputValuesAsASortedList() throws Exception {
        List<String> expected = new ArrayList<>(Arrays.asList(
                "Atlanta", "Baltimore", "Chicago", "Poughkeepsie"));
        LoadCityNames sut = new LoadCityNames();
        assertEquals(expected, sut.cityNames("src/integrationTest/resources/city-names.txt"));
    }
}
