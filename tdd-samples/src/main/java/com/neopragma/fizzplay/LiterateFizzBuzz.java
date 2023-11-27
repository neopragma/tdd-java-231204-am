package com.neopragma.fizzplay;

import java.util.stream.IntStream;

public class LiterateFizzBuzz implements FizzPlay {

    public String[] fizzbuzz() {
        /* Transform a list of integers */    return IntStream.range(1,101).mapToObj(
        /* into a list of strings such that any integer evenly divisible
           by both 3 and 5 becomes "FizzBuzz", */  i -> i % 15 == 0 ? "FizzBuzz"
        /* any other integer evenly divisible
           by 5 becomes "Buzz", */                 : i % 5 == 0 ? "Buzz"
        /* any other integer evenly divisible
           by 3 becomes "Fizz" */                 : i % 3 == 0 ? "Fizz"
        /* and other values are unchanged. */     : String.valueOf(i)).toArray(String[]::new);
    }
}
