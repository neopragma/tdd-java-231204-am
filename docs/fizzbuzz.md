# FizzBuzz 

The FizzBuzz problem is very common in the software field. Chances are good you've solved it yourself in more than one way and in more than one language. Our purpose here is to practice using TDD to develop fresh code - "greenfield" development. 

It's easy to do greenfield development with TDD, so we're going to practice that way before we get into the challenges of applying TDD to existing code. 

FizzBuzz originated as a children's arithmetic game. A group of children would count off, but when they reached a number evenly divisible by three they had to yell "Fizz!" instead of the number, and when they reached a number evenly divisible by five they had to yell "Buzz!." When they reached a number evenly divisible by both three and five, they had to yell "FizzBuzz!"

The problem is easily adapted to programming. The basic rules are:

Print or display a list of strings corresponding to a sequence of positive integers. For integers evenly divisible by 3, output the string "Fizz". For those evenly divisible by 5, output "Buzz". For those evenly divisible by both 3 and 5, output "FizzBuzz". For all other values, output the string representation of the integer. Yelling is optional.

So you'd get something like this for the range 1 through 15:

1  
2  
Fizz  
4  
Buzz  
Fizz  
7  
8  
Fizz  
Buzz  
11  
Fizz  
13  
14  
FizzBuzz

By default, we operate on the integers from 1 through 100 inclusive.

When using FizzBuzz for a code dojo, people often add requirements after participants have completed the basic functionality, like outputting "Baz" for numbers evenly divisible by seven, or allowing for any range of integers to be input at run time. Another potentially interesting question is what to do about the value, zero. Mathematically, it's evenly divisible by every number; but does that make sense to a human?

Depending on how rapidly you progress with the exercise, we might play with some additional requirements. Basically, we just need to support the basic rules for values from 1 to 100 inclusive.

Something to note about _separation of concerns_ - printing or displaying the values is one concern (I/O) and determining whether to substitute a word for the input value is a different concern (application logic). So, we want our solution to output an array or list of strings. If we really want to see the output printed, we should write a separate Java main class to do that.
