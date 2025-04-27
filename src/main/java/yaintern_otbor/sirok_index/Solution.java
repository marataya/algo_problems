package coderun.sirok_index;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        int[] prices = new int[n]; // Prices of cheese

        for (int i = 0; i < n; i++) {
            prices[i] = Integer.parseInt(reader.readLine());
        }

        // Use Binary Indexed Tree (Fenwick Tree) for efficient counting
        long result = countIncreasingPairs(prices, n);

        writer.write(String.valueOf(result));
        reader.close();
        writer.close();
    }

    private static long countIncreasingPairs(int[] arr, int n) {
        // Create a copy of the array and sort it
        int[] sortedArr = Arrays.copyOf(arr, n);
        Arrays.sort(sortedArr);

        // Map original values to their ranks (compressed coordinates)
        Map<Integer, Integer> rankMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            rankMap.put(sortedArr[i], i + 1);
        }

        // Use BIT to count pairs
        long[] bit = new long[n + 1];
        long result = 0;

        for (int i = 0; i < n; i++) {
            int rank = rankMap.get(arr[i]);
            // Count elements smaller than current that have been processed
            result += query(bit, rank - 1);
            // Update BIT
            update(bit, rank, 1);
        }

        return result;
    }

    // Query sum of frequencies up to index idx
    private static long query(long[] bit, int idx) {
        long sum = 0;
        while (idx > 0) {
            sum += bit[idx];
            idx -= (idx & -idx); // Remove least significant bit
        }
        return sum;
    }

    // Update frequency at index idx
    private static void update(long[] bit, int idx, int val) {
        while (idx < bit.length) {
            bit[idx] += val;
            idx += (idx & -idx); // Add least significant bit
        }
    }
}
