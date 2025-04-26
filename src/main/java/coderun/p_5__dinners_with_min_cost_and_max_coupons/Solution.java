package coderun.p_5__dinners_with_min_cost_and_max_coupons;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
5. Кафе
Сложная
Около Петиного университета недавно открылось новое кафе, в котором действует следующая система скидок: при каждой покупке более чем на 100 рублей покупатель получает купон, дающий право на один бесплатный обед (при покупке на сумму 100 рублей и меньше такой купон покупатель не получает).
Однажды Пете на глаза попался прейскурант на ближайшие N дней. Внимательно его изучив, он решил, что будет обедать в этом кафе все N дней, причем каждый день он будет покупать в кафе ровно один обед. Однако стипендия у Пети небольшая, и поэтому он хочет по максимуму использовать предоставляемую систему скидок так, чтобы его суммарные затраты были минимальны. Требуется найти минимально возможную суммарную стоимость обедов и номера дней, в которые Пете следует воспользоваться купонами.

Формат ввода
В первой строке входного файла записано целое число N (0 ≤ N ≤ 100). В каждой из последующих N строк записано одно целое число, обозначающее стоимость обеда в рублях на соответствующий день. Стоимость — неотрицательное целое число, не превосходящее 300.

Формат вывода
В первой строке выдайте минимальную возможную суммарную стоимость обедов. Во второй строке выдайте два числа K1 и K2 — количество купонов, которые останутся неиспользованными у Пети после этих N дней и количество использованных им купонов соответственно.

В последующих K2 строках выдайте в возрастающем порядке номера дней, когда Пете следует воспользоваться купонами. Если существует несколько решений с минимальной суммарной стоимостью, то выдайте то из них, в котором значение K1 максимально (на случай, если Петя когда-нибудь ещё решит заглянуть в это кафе). Если таких решений несколько, выведите любое из них.

Ограничения
Ограничение времени 1 с

Ограничение памяти
64 МБ

Пример 1
Ввод
5
35
40
101
59
63
Вывод
235
0 1
5
* */

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());

        int[] prices = new int[n]; // Цены обедов на каждый день
        for (int i = 0; i < n; i++) {
            prices[i] = Integer.parseInt(reader.readLine());
        }

        // dp[i][j] - минимальная стоимость обедов за i дней, j купонов на руках
        int[][] dp = new int[n + 1][n + 1];
        // days[i][j] - список дней, в которые были использованы купоны для достижения dp[i][j]
        List<Integer>[][] days = new ArrayList[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                days[i][j] = new ArrayList<>();
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[0][0] = 0;

        // base case 0 days 0 coupons
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (dp[i][j] == Integer.MAX_VALUE) continue;

                // Вариант 1: Заплатить за обед
                int newCoupons = j + (prices[i] > 100 ? 1 : 0);
                if (newCoupons <= n && dp[i][j] + prices[i] < dp[i+1][newCoupons]) {
                    dp[i+1][newCoupons] = dp[i][j] + prices[i];
                    days[i+1][newCoupons] = new ArrayList<>(days[i][j]);
                }

                // Вариант 2: Использовать купон, если есть
                if (j > 0 && dp[i][j] < dp[i+1][j-1]) {
                    dp[i+1][j-1] = dp[i][j];
                    days[i+1][j-1] = new ArrayList<>(days[i][j]);
                    days[i+1][j-1].add(i+1); // Добавляем день, когда использовали купон (1-индексация)
                }
            }
        }

        // Находим минимальную стоимость и максимальное количество оставшихся купонов
        int minCost = Integer.MAX_VALUE;
        int maxRemainingCoupons = 0;

        for (int j = 0; j <= n; j++) {
            if (dp[n][j] < minCost) {
                minCost = dp[n][j];
                maxRemainingCoupons = j;
            } else if (dp[n][j] == minCost && j > maxRemainingCoupons) {
                maxRemainingCoupons = j;
            }
        }

        List<Integer> usedCouponDays = days[n][maxRemainingCoupons];

        // Вывод результатов
        writer.write(String.valueOf(minCost));
        writer.newLine();
        writer.write(maxRemainingCoupons + " " + usedCouponDays.size());
        writer.newLine();

        for (int day : usedCouponDays) {
            writer.write(String.valueOf(day));
            writer.newLine();
        }

        reader.close();
        writer.close();
    }
}
