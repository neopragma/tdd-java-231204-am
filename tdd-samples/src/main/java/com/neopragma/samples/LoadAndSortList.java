package com.neopragma.samples;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoadAndSortList {

    public List<String> createListFrom(BufferedReader inputValues) throws IOException {
        if (null == inputValues) {
            throw new IllegalArgumentException();
        }
        List<String> outputList = new ArrayList<>();
        String line = "";
        while ((line = inputValues.readLine()) != null) {
            outputList.add(line);
        }
        Collections.sort(outputList);
        return outputList;
    }
}
