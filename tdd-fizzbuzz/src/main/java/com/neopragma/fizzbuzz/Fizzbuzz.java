package com.neopragma.fizzbuzz;

public class Fizzbuzz 
{
    public String processNumber(int number) {
        return number % 15 == 0 ? "FizzBuzz" :
                number % 3 == 0 ? "Fizz" :
               number % 5 == 0 ? "Buzz" :
                       String.valueOf(number);
    }
}
