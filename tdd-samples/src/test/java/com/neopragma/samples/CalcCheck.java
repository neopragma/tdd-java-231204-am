package com.neopragma.samples;
/**
 * Unit test cases for the sum() method of class Calc
 * written without a unit test library/framework.
 */
public class CalcCheck {
    public void runTests() {
        Calc calc = new Calc();
        Double res = calc.sum(1.5,2.0);
        if (res.equals(2.5)) {
            ;
        } else {
            System.out.println("Test 1 FAIL: Expected calc.sum(1.5,2.0) to equal 2.5, but was " + res);
        }
        res = calc.sum(1.5,2.0,3.2);
        if (res.equals(6.7)) {
            ;
        } else {
            System.out.println("Test 2 FAIL: Expected calc.sum(1.5,2.0,3.2) to equal 6.7, but was " + res);
        }
    }

    public static void main(String[] args) {
        CalcCheck check = new CalcCheck();
        check.runTests();
        System.exit(0);
    }
}
