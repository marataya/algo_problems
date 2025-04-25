package coderun.p_3__route_with_max_cost;

import java.io.*;


public class Solution {

    public static class Result {
        int maxCost;    // Maximum cost to reach a cell
        String path;     // Path taken to reach that cell (e.g., "DDRR")

        public Result(int maxCost, String path) {
            this.maxCost = maxCost;
            this.path = path;
        }

        public int getMaxCost() {
            return maxCost;
        }

        public String getPath() {
            return path;
        }
    }

    public static Result findMaxCost(int[][] grid, int n, int m) {
        Result[][] memo = new Result[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                memo[i][j] = null;
            }
        }
        return maxCostPath(n-1, m-1, grid, memo);
    }

    public static Result maxCostPath(int row, int col, int[][] grid, Result[][] memo) {
        if (row == 0 && col == 0) {
            return new Result(grid[0][0], "");
        }

        if (row < 0 || col < 0) {
            return new Result(Integer.MIN_VALUE, "");
        }

        if (memo[row][col] != null) {
            return memo[row][col];
        }

        Result up = maxCostPath(row - 1, col, grid, memo);
        Result left = maxCostPath(row, col - 1, grid, memo);

        // maxCost for current cell
        Result currResult;
        if (up.getMaxCost() > left.getMaxCost()) {
            currResult = new Result(up.getMaxCost() + grid[row][col], up.getPath() == "" ? up.getPath() + "D" : up.getPath() + " D");
        } else {
            currResult = new Result(left.getMaxCost() + grid[row][col], left.getPath() == "" ? left.getPath() + "R" : left.getPath() + " R");
        }

        memo[row][col] = currResult;
        return currResult;
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

        var result = findMaxCost(grid, n, m);

        writer.write(String.valueOf(result.getMaxCost()));
        writer.newLine();
        writer.write(String.valueOf(result.getPath()));

        reader.close();
        writer.close();
    }
}
