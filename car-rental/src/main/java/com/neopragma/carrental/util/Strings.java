package com.neopragma.carrental.util;

public class Strings {

    public static boolean isEmpty(String s) {
        return s == null
                || s.trim().equals("")
                || s.trim().equals("\t")
                || s.trim().equals("\n");
    }

    public static boolean isNotEmpty(String s) {
        return s != null
                && !s.trim().equals("")
                && !s.trim().equals("\t")
                && !s.trim().equals("\n");
    }

    public static void assertValidStringArgs(String... args) {
        for (String arg : args) {
            if (isEmpty(arg)) {
                throw new RuntimeException("Invalid String argument value.");
            }
        }
    }
}
