package coderun.p_5__dinners_with_min_cost_and_max_coupons;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {
    private void testSolution(String input, String expectedOutput) throws IOException {
        // Redirect System.in and System.out
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        try {
            // Set up input
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            // Capture output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            // Run the solution
            Solution.main(new String[0]);

            // Compare output
            assertEquals(expectedOutput, outputStream.toString());

        } finally {
            // Restore original streams
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }

    private void testSolutionAndVerifyMinCost(String input) throws IOException {
        // Redirect System.in and System.out
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        try {
            // Set up input
            ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStream);

            // Capture output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            // Run the solution
            Solution.main(new String[0]);

            // Parse the input to calculate expected minimum cost
            Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
            int n = scanner.nextInt();
            int[] prices = new int[n];
            for (int i = 0; i < n; i++) {
                prices[i] = scanner.nextInt();
            }

            // Parse the output
            Scanner outputScanner = new Scanner(outputStream.toString());
            int actualMinCost = outputScanner.nextInt();
            int unusedCoupons = outputScanner.nextInt();
            int usedCoupons = outputScanner.nextInt();

            List<Integer> couponDays = new ArrayList<>();
            for (int i = 0; i < usedCoupons; i++) {
                if (outputScanner.hasNextInt()) {
                    couponDays.add(outputScanner.nextInt());
                }
            }

            // Verify the solution
            int calculatedCost = 0;
            int generatedCoupons = 0;

            // Mark days where coupons are used
            boolean[] useCouponOnDay = new boolean[n + 1];
            for (int day : couponDays) {
                useCouponOnDay[day] = true;
            }

            // Calculate the cost
            for (int i = 0; i < n; i++) {
                if (useCouponOnDay[i + 1]) {
                    // Used a coupon on this day
                    continue;
                } else {
                    // Paid for the meal
                    calculatedCost += prices[i];
                    if (prices[i] > 100) {
                        generatedCoupons++;
                    }
                }
            }

            // Verify that the total number of coupons is correct
            assertEquals(generatedCoupons, unusedCoupons + usedCoupons, "Total coupons mismatch");

            // Verify that the minimum cost is correct
            assertEquals(calculatedCost, actualMinCost, "Minimum cost mismatch");

            // Verify that coupon days are in ascending order
            for (int i = 1; i < couponDays.size(); i++) {
                assertTrue(couponDays.get(i) > couponDays.get(i - 1), "Coupon days not in ascending order");
            }

        } finally {
            // Restore original streams
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }


    @Test
    void testExampleCase() throws IOException {
        // Input from the example
        String input = "5\n35\n40\n101\n59\n63\n";

        // Expected output
        String expectedOutput = "235\n0 1\n5\n";

        testSolution(input, expectedOutput);
    }

    @Test
    void testAllCheapMeals() throws IOException {
        // Test with all meals <= 100 (should not generate any coupons)
        String input = "5\n50\n60\n70\n80\n90\n";

        // Expected output: sum of all prices, 0 unused coupons, 0 used coupons
        String expectedOutput = "350\n0 0\n";

        testSolution(input, expectedOutput);
    }

    @Test
    void testLargeInput() throws IOException {
        // Test with a larger input
        StringBuilder input = new StringBuilder("20\n");
        for (int i = 0; i < 10; i++) {
            input.append("150\n"); // Generate coupons
        }
        for (int i = 0; i < 10; i++) {
            input.append("50\n"); // Cheap meals to use coupons on
        }

        testSolutionAndVerifyMinCost(input.toString());
    }
}
