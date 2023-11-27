package com.neopragma.fizzplay;

import java.util.stream.IntStream;

public class StreamFizzBuzz implements FizzPlay {

    public String[] fizzbuzz() {
        return IntStream.range(1, 101)
                .mapToObj(i -> i % 15 == 0 ? "FizzBuzz"
                    : i % 5 == 0 ? "Buzz"
                    : i % 3 == 0 ? "Fizz"
                    : String.valueOf(i))
                .toArray(String[]::new);
    }
}
