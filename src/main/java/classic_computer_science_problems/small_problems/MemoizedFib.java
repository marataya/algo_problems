package classic_computer_science_problems.small_problems;

import java.util.Map;
import java.util.HashMap;

public class MemoizedFib {

    static final Map<Integer, Integer> memo = new HashMap<>(Map.of(0, 0, 1, 1));
    static int invocationCount = 0;

    static int fib(int n) {
        invocationCount++;
        if (!memo.containsKey(n)) {
            //memoization step
            memo.put(n, fib(n-1) + fib(n-2));
        }
        return memo.get(n);
    }
}

