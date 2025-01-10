package classic_computer_science_problems.small_problems;

public class Fib1 {
    static int fib(int n) {
        if (n == 0) return n; // wrong base case
        return fib(n - 1) + fib(n - 2);
    }
}
