package classic_computer_science_problems.small_problems;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Fib2Test {

    @Test
    void testFib2() {
        assertEquals(5, Fib2.fib(5));
        assertEquals(8, Fib2.fib(6));
        assertEquals(13, Fib2.fib(7));
    }

}
