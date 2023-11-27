package com.neopragma.samples;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoadAndSortListTest {
    @Mock
    BufferedReader inputData;

    @Test
    public void itReturnsTheInputValuesAsASortedList() throws Exception {
        when(inputData.readLine())
                .thenReturn("Poughkeepsie")
                .thenReturn("Baltimore")
                .thenReturn("Chicago")
                .thenReturn("Atlanta")
                .thenReturn(null);
        List<String> expected = new ArrayList<>(Arrays.asList(
                "Atlanta", "Baltimore", "Chicago", "Poughkeepsie"));
        LoadAndSortList sut = new LoadAndSortList();
        assertEquals(expected, sut.createListFrom(inputData));
    }
}
