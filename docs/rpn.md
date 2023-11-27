# Reverse Polish Notation (RPN) Calculator 

This is a simple programming problem suitable for practicing greenfield development using TDD. 

Reverse Polish Notation is also called _postfix notation_, and sometimes _reverse Łukasiewicz notation_. Operators follow their operands - hence the term, "postfix". So if we wanted to sum the values 4 and 5, we would write: 

4 5 + 

The way we usually write that expression is called _infix notation_, because the operators are written in between their operands, like this:

4 + 5 

Why "reverse?" It's the reverse of _prefix notation_, which is also called _Polish notation_: 

\+ 4 5 

Complex arithmetic expressions can be written in postfix notation without the need for parentheses. 

(4 + 5) \* 2 becomes 

4 5 + 2 \* 

RPN calculators are usually implemented as a _stack_. The current value of the calculation is the value at the top of the stack. 

Enter 4, stack depth is 1, value is 4   
Enter 5, stack depth is 2, value is 5   
Enter +, stack depth is 1, value is 9  
Enter 2, stack depth is 2, value is 2   
Enter \*, stack depth is 1, value is 18 

As a TDD exercise, we want to build up the logic for an RPN calculator through microtests. 
