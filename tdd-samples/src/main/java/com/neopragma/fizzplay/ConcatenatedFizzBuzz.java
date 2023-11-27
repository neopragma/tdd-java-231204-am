package com.neopragma.fizzplay;

import java.util.stream.IntStream;

public class ConcatenatedFizzBuzz implements FizzPlay {

    public String[] fizzbuzz() {
        StringBuilder sb = new StringBuilder();
        IntStream.range(1, 101).forEachOrdered(n -> {
            sb.append(
                n % 15 == 0 ? "FizzBuzz"
              : n %  5 == 0 ? "Buzz"
              : n %  3 == 0 ? "Fizz"
              : String.valueOf(n)
            );
            sb.append(" ");
        });
        return sb.toString().trim().split("\\W+", 100);
    }
}
