package classic_computer_science_problems.small_problems;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Fib1Test {
    @Test
    void testFib1() {
        assertThrows(StackOverflowError.class, () -> Fib1.fib(5));
    }

}
