package com.neopragma.fizzplay;

import java.util.stream.IntStream;

public class OverlyFactoredFizzBuzz implements FizzPlay {

    public String[] fizzbuzz() {
        return IntStream.range(1, 101)
                .mapToObj(input -> isEvenlyDivisibleBy(input, 15) ? "FizzBuzz"
                        : isEvenlyDivisibleBy(input, 5) ? "Buzz"
                        : isEvenlyDivisibleBy(input, 3) ? "Fizz"
                        : String.valueOf(input))
                .toArray(String[]::new);
    }
    private boolean isEvenlyDivisibleBy(int input, int divisor) {
        return input % divisor == 0;
    }
}
