package com.neopragma.fizzbuzz;

public class Fizzbuzz 
{
    public String processNumber(int i) {
        String result = "";
        if (i % 15 == 0) {
            result = "FizzBuzz";
        } else if (i % 5 == 0) {
            result = "Buzz";
        } else if (i % 3 == 0) {
            result = "Fizz";
        } else {
            result = String.valueOf(i);
        }
        return result;
    }
}
