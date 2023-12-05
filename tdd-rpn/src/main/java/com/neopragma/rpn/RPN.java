package com.neopragma.rpn;

import java.util.Stack;

public class RPN {

    Stack<Double> calc = new Stack<>();
    public Double enter(Double number) {
        calc.push(number);
        return calc.peek();
    }

    public Double enterOperator(String operator) {
        Double result = 0.0;
        switch(operator) {
            case "+":
                calc.push(calc.pop() + calc.pop());
                break;
            case "-":
                calc.push(calc.pop() - calc.pop());
                break;
            case "*":
                calc.push(calc.pop() * calc.pop());
                break;
            case "/":
                calc.push(calc.pop() / calc.pop());
                break;
        }
        return calc.peek();
    }
}
