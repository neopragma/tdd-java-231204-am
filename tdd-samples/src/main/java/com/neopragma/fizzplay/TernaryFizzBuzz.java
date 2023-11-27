package com.neopragma.fizzplay;

public class TernaryFizzBuzz implements FizzPlay {

    public String[] fizzbuzz() {
        String[] result = new String[100];
        for (int inputValue = 1 ; inputValue < 101 ; inputValue++) {
            result[inputValue - 1] =
                    inputValue % 15 == 0 ? "FizzBuzz"
                  : inputValue %  5 == 0 ? "Buzz"
                  : inputValue %  3 == 0 ? "Fizz"
                  : String.valueOf(inputValue);
        }
        return result;
    }
}
