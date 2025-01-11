package classic_computer_science_problems.small_problems;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;

class IterativeFibTest {

    @Test
    void testIterativeFibOutputs() {
        assertEquals(5, Fib2.fib(5));
        assertEquals(8, Fib2.fib(6));
        assertEquals(13, Fib2.fib(7));
    }
    @Test
    public void testIterativeFibInvocationCountForInput20EqualTo1() {
        try (MockedStatic<IterativeFib> mockedFibonacci = Mockito.mockStatic(IterativeFib.class)) {
            // Define the behavior of the mock static method
            mockedFibonacci.when(() -> IterativeFib.fib(anyInt())).thenCallRealMethod();
            // Call the static method
            IterativeFib.fib(20);
            // Verify the number of invocations
            mockedFibonacci.verify(() -> IterativeFib.fib(anyInt()), times(1));
        }
    }
}
