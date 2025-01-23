package classic_computer_science_problems.small_problems;

public class PiCalculator {
    public static double calculatePi(int nTerms) {
        final double numerator = 4.0;
        double denominator = 1.0;
        double sign = 1.0;
        double pi = 0.0;
        for (int i = 0; i < nTerms; i++) {
            pi += sign * (numerator / denominator);
            denominator += 2.0;
            sign *= -1.0;
        }
        return pi;
    }

    public static void main(String[] args) {
        System.out.println(calculatePi(1_000_000));
        System.out.println(calculatePi(10_000_000));
        System.out.println(calculatePi(100_000_000));
    }
}
