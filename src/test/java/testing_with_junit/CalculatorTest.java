package testing_with_junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import testing_with_junit.runners.CustomTestRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(CustomTestRunner.class)
class CalculatorTest {
    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        double result = calculator.add(10, 50);
        assertEquals(60, result, 0);
    }
}
