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
import java.util.Arrays;

public class Solution_Top_down {
    private static int minCost(int row, int col, int[][] grid, int[][] memo) {
        // Base case: starting cell
        if (row == 0 && col == 0) {
            return grid[0][0];
        }

        // Base case: invalid cell
        if (row < 0 || col < 0) {
            return Integer.MAX_VALUE; // Or any very large value
        }

        // Check if the result is already memoized
        if (memo[row][col] != -1) {
            return memo[row][col];
        }

        // Calculate the minimum cost from the top and left cells
        int up = minCost(row - 1, col, grid, memo);
        int left = minCost(row, col - 1, grid, memo);

        // Calculate the minimum cost for the current cell
        int minCost = grid[row][col] + Math.min(up, left);

        // Memoize the result
        memo[row][col] = minCost;
        return minCost;
    }

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

        // Create a memoization table to store the minimum costs
        int[][] memo = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1); // Initialize with -1 to indicate uncalculated
        }

        writer.write(String.valueOf(minCost(n - 1, m - 1, grid, memo)));

        reader.close();
        writer.close();
    }
}
