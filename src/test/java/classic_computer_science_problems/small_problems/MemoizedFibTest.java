package classic_computer_science_problems.small_problems;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;

class MemoizedFibTest {
    @Test
    public void testMemoizedFibInvocationCountForInput20EqualTo39() {
        try (MockedStatic<MemoizedFib> mockedFibonacci = Mockito.mockStatic(MemoizedFib.class)) {
            // Define the behavior of the mock static method
            mockedFibonacci.when(() -> MemoizedFib.fib(anyInt())).thenCallRealMethod();
            // Call the static method
            MemoizedFib.fib(20);
            // Verify the number of invocations
            mockedFibonacci.verify(() -> MemoizedFib.fib(anyInt()), times(39));
        }
    }

}

