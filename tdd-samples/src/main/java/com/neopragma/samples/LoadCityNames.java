package com.neopragma.samples;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LoadCityNames {

    public List<String> cityNames(String fileName) throws IOException {
        LoadAndSortList loader = new LoadAndSortList();
        return loader.createListFrom(new BufferedReader(new FileReader(fileName)));
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Please enter a file name as the first argument.");
            System.exit(1);
        }
        LoadCityNames app = new LoadCityNames();
        app.cityNames(args[0]);
    }
}
