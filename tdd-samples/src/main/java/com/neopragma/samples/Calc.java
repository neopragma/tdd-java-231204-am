package com.neopragma.samples;

/**
 * A class to serve as a test subject for various unit test examples.
 */
public class Calc {
    public Double sum(Double... val) {
        Double res = 0.0;
        for (Double num : val) {
            res += num;
        }
        return res;
    }
}
