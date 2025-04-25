package coderun.p_2__the_cheapest_way;

//You're asking for ways to solve the "Cheapest Path" problem in a grid using dynamic programming. Here's a breakdown of the common approaches and how to apply them to this problem:
//
//Understanding the Problem
//
//You have an N×M grid where each cell has a cost (food weight).
//You start at the top-left cell (0, 0).
//You can only move right or down.
//The goal is to find the minimum total cost to reach the bottom-right cell (N−1, M−1), including the cost of the starting and ending cells.

//Пример 1
//Ввод
//5 5
//1 1 1 1 1
//3 100 100 100 100
//1 1 1 1 1
//2 2 2 2 1
//1 1 1 1 1
//Вывод
//11

import java.io.*;

public class Solution_Bottom_up {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // Read input
        String[] nm = reader.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] row = reader.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(row[j]);
            }
        }

        // Initialize the DP table
        int[][] dp = new int[n][m];

        // Initialize the starting cell's cost
        dp[0][0] = grid[0][0];

        // Fill the first row (only moving right is possible)
        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // Fill the first column (only moving down is possible)
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        // Fill the rest of the DP table
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        writer.write(String.valueOf(dp[n - 1][m - 1]));

        reader.close();
        writer.close();
    }
}
