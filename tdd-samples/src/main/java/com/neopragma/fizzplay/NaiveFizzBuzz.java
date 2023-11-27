package com.neopragma.fizzplay;

public class NaiveFizzBuzz implements FizzPlay {

    public String[] fizzbuzz() {
        String[] result = new String[100];
        for (int inputValue = 1 ; inputValue < 101 ; inputValue++) {
            if (inputValue % 15 == 0)
                result[inputValue - 1] = "FizzBuzz";
            else if (inputValue % 5 == 0)
                result[inputValue - 1] = "Buzz";
            else if (inputValue % 3 == 0)
                result[inputValue - 1] = "Fizz";
            else
                result[inputValue - 1] = String.valueOf(inputValue);
        }
        return result;
    }
}
