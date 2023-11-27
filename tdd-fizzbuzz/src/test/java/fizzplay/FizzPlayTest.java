package fizzplay;

import com.neopragma.fizzbuzz.Fizzbuzz;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test cases for the sum() method of class Calc
 * using the JUnit test library - basic implementation.
 */
public class FizzPlayTest {

    private Fizzbuzz sut;
    @BeforeEach
    public void initializeSut() {
        sut = new Fizzbuzz();
    }

    @ParameterizedTest
    @MethodSource("provideValuesForFizzbuzz")
    public void checkFizzBuzz(int number, String result) {
        assertEquals(result, sut.processNumber(number));
    }

    private static Stream<Arguments> provideValuesForFizzbuzz() {
        return Stream.of(
                Arguments.of(2, "2"),
                Arguments.of(3, "Fizz"),
                Arguments.of(5, "Buzz"),
                Arguments.of(15, "FizzBuzz")
        );
    }

}

