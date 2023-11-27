package com.neopragma.rpn;

import java.util.Stack;

public class RPN
{
    private final Stack<String> stack;
    public RPN() {
        stack = new Stack<String>();
    }

    public String enter(String token) {
        stack.push(token);
        return stack.peek();
    }
}
