package core_java;

import java.util.ArrayList;

/**
 * Write a program that stores Pascal’s triangle up to a given n in an ArrayList<ArrayList<Integer>>.
 * */
public class PascalTriangle {

    public static void main(String[] args) {
        int n = 5;
        ArrayList<ArrayList<Integer>> triangle = generatePascalTriangle(n);
        System.out.println(triangle);
        StringBuilder display = new StringBuilder();
        for (int i = 0; i < triangle.size(); i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                display.append(triangle.get(i).get(j) + "\s");
            }
            display.append("\n");
        }
        System.out.println(display);
    }

    private static ArrayList<ArrayList<Integer>> generatePascalTriangle(int n) {
        ArrayList<ArrayList<Integer>> triangle = new ArrayList<>();

        for (int row = 0; row < n; row++) {
            ArrayList<Integer> currentRow = new ArrayList<>();
            currentRow.add(1);

            if (row > 0) {
                ArrayList<Integer> previousRow = triangle.get(row - 1);
                for (int col = 1; col < row; col++) {
                    currentRow.add(previousRow.get(col - 1) + previousRow.get(col));
                }
                currentRow.add(1);
            }

            triangle.add(currentRow);
        }
        return triangle;
    }
}
