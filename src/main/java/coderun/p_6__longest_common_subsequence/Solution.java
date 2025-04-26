package coderun.p_6__longest_common_subsequence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
*
6. НОП с восстановлением ответа
Средняя
Даны две последовательности, требуется найти и вывести их наибольшую общую подпоследовательность.

Напомним:

Последовательность называется подпоследовательностью последовательности, если получается из удалением нескольких (возможно, нуля или всех) элементов.
Наибольшая общая подпоследовательность - последовательность наибольшей длины, которая является подпоследовательностью обеих последовательностей.

* Формат ввода
В первой строке входных данных содержится число N – длина первой последовательности (1 ≤ N ≤ 1000). Во второй строке заданы члены первой последовательности (через пробел) – целые числа, не превосходящие 10000 по модулю.
В третьей строке записано число M – длина второй последовательности (1 ≤ M ≤ 1000). В четвертой строке задаются члены второй последовательности (через пробел) – целые числа, не превосходящие 10000 по модулю.

Формат вывода
Требуется вывести наибольшую общую подпоследовательность данных последовательностей, через пробел. Если таких подпоследовательностей несколько, то можно вывести любую.

Примечание
В примере 2 существует сразу три наибольшие общие подпоследовательности.
1) 1
2) 2
3) 3
Любая из них будет правильным ответом.

Ограничения
Ограничение времени
1 с

Ограничение памяти
64 МБ

Пример 1
Ввод
3
1 2 3
3
2 3 1
Вывод
2 3
Пример 2
Ввод
3
1 2 3
3
3 2 1
Вывод
1

* */
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // Read input
        int n = Integer.parseInt(reader.readLine());
        int[] seq1 = new int[n];
        String[] row = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            seq1[i] = Integer.parseInt(row[i]);
        }
        int m = Integer.parseInt(reader.readLine());
        int[] seq2 = new int[m];
        row = reader.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            seq2[i] = Integer.parseInt(row[i]);
        }


        // Вычисление длины НОП и таблицы для восстановления ответа
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (seq1[i - 1] == seq2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                printDP(dp, i, j);
            }
        }

        // Восстановление ответа
        List<Integer> lcs = new ArrayList<>();
        int i = n, j = m;
        while (i > 0 && j > 0) {
            if (seq1[i - 1] == seq2[j - 1]) {
                lcs.add(seq1[i - 1]);
                i--;
                j--;
                printLCS(lcs, i, j);
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
                printLCS(lcs, i, j);
            } else {
                j--;
                printLCS(lcs, i, j);
            }
        }
        //Разворачиваем список, так как восстанавливали с конца
        StringBuilder sb = new StringBuilder();
        for (int k = lcs.size() - 1; k >= 0; k--) {
            sb.append(lcs.get(k)).append(" ");
        }

        System.out.println(sb.toString().trim());
    }


    // Функция для вывода таблицы dp
    public static void printDP(int[][] dp, int ival, int jval) {
        System.out.printf("Таблица DP, I=%d, J=%d:%n", ival, jval);
        for (int i = 0; i <= dp.length-1; i++) {
            for (int j = 0; j <= dp[0].length-1; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    // output lcs table
    public static void printLCS(List<Integer> lcs, int i, int j) {
        System.out.printf("Таблица LCS, I=%d, J=%d:%n", i, j);
        for (var el : lcs) {
            System.out.printf("%d\t", el);
        }
        System.out.println();
    }
}
