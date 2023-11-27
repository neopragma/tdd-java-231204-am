package com.neopragma.fizzplay;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class FizzPlayTest {

    private String[] expected = new String[] {
            "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz",
            "11", "Fizz", "13", "14", "FizzBuzz", "16", "17", "Fizz", "19", "Buzz",
            "Fizz", "22", "23", "Fizz", "Buzz", "26", "Fizz", "28", "29", "FizzBuzz",
            "31", "32", "Fizz", "34", "Buzz", "Fizz", "37", "38", "Fizz", "Buzz",
            "41", "Fizz", "43", "44", "FizzBuzz", "46", "47", "Fizz", "49", "Buzz",
            "Fizz", "52", "53", "Fizz", "Buzz", "56", "Fizz", "58", "59", "FizzBuzz",
            "61", "62", "Fizz", "64", "Buzz", "Fizz", "67", "68", "Fizz", "Buzz",
            "71", "Fizz", "73", "74", "FizzBuzz", "76", "77", "Fizz", "79", "Buzz",
            "Fizz", "82", "83", "Fizz", "Buzz", "86", "Fizz", "88", "89", "FizzBuzz",
            "91", "92", "Fizz", "94", "Buzz", "Fizz", "97", "98", "Fizz", "Buzz"
    };

    @ParameterizedTest
    @MethodSource("instances")
    public void checkFizzBuzz(FizzPlay sut) throws Exception {
        assertArrayEquals(expected, sut.fizzbuzz());
    }
    private static Stream<Arguments> instances() throws Exception {
        return Stream.of(
                Arguments.of(instantiate("BruteForceFizzBuzz")),
                Arguments.of(instantiate("NaiveFizzBuzz")),
                Arguments.of(instantiate("TernaryFizzBuzz")),
                Arguments.of(instantiate("StreamFizzBuzz")),
                Arguments.of(instantiate("LiterateFizzBuzz")),
                Arguments.of(instantiate("OverlyFactoredFizzBuzz")),
                Arguments.of(instantiate("ConcatenatedFizzBuzz")),
                Arguments.of(instantiate("BrainBuzz"))
        );
    }

    private static FizzPlay instantiate(String className) throws Exception {
        String packageName = "com.neopragma.fizzplay.";
        return (FizzPlay) Class.forName(packageName + className)
                    .getDeclaredConstructor()
                    .newInstance();
    }

}
