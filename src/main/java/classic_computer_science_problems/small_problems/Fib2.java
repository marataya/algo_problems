package classic_computer_science_problems.small_problems;

public class Fib2 {
    static int fib(int n) {
        if (n < 2) return n;
        return fib(n - 1) + fib(n - 2);
    }
}
