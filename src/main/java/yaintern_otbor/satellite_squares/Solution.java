package coderun.satellite_squares;


import java.io.*;
import java.util.Arrays;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] wh = reader.readLine().split(" ");
        int w = Integer.parseInt(wh[0]);
        int h = Integer.parseInt(wh[1]);
        int n = Integer.parseInt(reader.readLine());

        int[][] rects = new int[n][4];
        for (int i = 0; i < n; i++) {
            String[] row = reader.readLine().split(" ");
            rects[i][0] = Integer.parseInt(row[0]); //xL
            rects[i][1] = Integer.parseInt(row[1]); //yL
            rects[i][2] = Integer.parseInt(row[2]); //xR
            rects[i][3] = Integer.parseInt(row[3]); //yR
        }

        int maxX = 0, maxY = 0;
        for (int i = 0; i < n; i++) {
            maxX = Math.max(maxX, rects[i][2]);
            maxY = Math.max(maxY, rects[i][3]);
        }

        boolean[][] current = new boolean[maxX + 2][maxY + 2];
        boolean[][] next = new boolean[maxX + 2][maxY + 2];

        int[] firstRect = rects[0];
        for (int x = firstRect[0]; x <= firstRect[2]; x++) {
            for (int y = firstRect[1]; y <= firstRect[3]; y++) {
                current[x][y] = true;
            }
        }

        for (int i = 1; i < n; i++) {
            int[] rect = rects[i];

            for (int x = 0; x <= maxX + 1; x++) {
                Arrays.fill(next[x], false);
            }


            for (int x = rect[0]; x <= rect[2]; x++) {
                for (int y = rect[1]; y <= rect[3]; y++) {
                    if (checkAdjacent(current, x, y, maxX, maxY)) {
                        next[x][y] = true;
                    }
                }
            }

            boolean[][] temp = current;
            current = next;
            next = temp;
        }

        boolean possible = false;
        for (int x = 0; x <= maxX; x++) {
            for (int y = 0; y <= maxY; y++) {
                if (current[x][y]) {
                    possible = true;
                    break;
                }
            }
            if (possible) break;
        }

        writer.write(possible ? "Yes" : "No");

        reader.close();
        writer.close();
    }

    private static boolean checkAdjacent(boolean[][] grid, int x, int y, int w, int h) {
        if (x >= 0 && x <= w && y >= 0 && y <= h && grid[x][y]) {
            return true;
        }

        if (x > 0 && grid[x-1][y]) {
            return true;
        }

        if (x < w && grid[x+1][y]) {
            return true;
        }

        if (y > 0 && grid[x][y-1]) {
            return true;
        }

        if (y < h && grid[x][y+1]) {
            return true;
        }

        return false;
    }
}
