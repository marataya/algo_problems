package classic_computer_science_problems.small_problems;

public class IterativeFib {
    static int fib(int n) {
        int last = 0, next = 1;
        for (int i = 0; i < n; i++) {
            int oldLast = last;
            last = next;
            next = oldLast + next;
        }
        return last;
    }
}
