package coderun.p_4__knight_moves;

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // Read input
        String[] nm = reader.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        // Initialize the DP table
        int[][] dp = new int[n][m];

        // Initialize the starting cell's cost
        dp[0][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i - 1 >= 0 && j - 2 >= 0) dp[i][j] += dp[i - 1][j - 2];
                if (i - 2 >= 0 && j - 1 >= 0) dp[i][j] += dp[i - 2][j - 1];
            }
        }

        writer.write(String.valueOf(dp[n-1][m-1]));

        reader.close();
        writer.close();
    }
}
