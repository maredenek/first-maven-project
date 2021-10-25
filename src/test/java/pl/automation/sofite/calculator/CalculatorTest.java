package pl.automation.sofite.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class CalculatorTest {

    @Test
    void shouldReturnCorrectSum() {
        Assertions.assertEquals(8, Calculator.add(4, 4), "should return correct sum");
    }

    @Test
    @Disabled("Enable when subtract method is fixed")
    void shouldReturnCorrectSubtractingResult() {
        Assertions.assertEquals(1, Calculator.subtract(4, 3), "should return correct subtracting result");
    }

    @Test
    void shouldReturnCorrectMultiplyingResult() {
        Assertions.assertEquals( 16, Calculator.multiply(4, 4), "should return correct multiplying result");
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    void shouldReturnCorrectMultiplyingResultParametrized(Integer firstNum, Integer secondNum, Integer expectedResult) {
        Integer actualResult = Calculator.multiply(firstNum, secondNum);
        Assertions.assertEquals(expectedResult, actualResult, "should return correct multiplying result");
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.of(1, 1, 2),
                Arguments.of(3, 3, 9),
                Arguments.of(4, 4, 16)
        );
    }

}
