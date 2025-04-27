package coderun.effective_loader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
* Эффективные погрузчики
Ограничение времени	1 секунда	15 секунд
Ограничение памяти	64 Мб	64 Мб

* На крупном заводе решили проверить эффективность работы погрузчиков и провели эксперимент: установили на складах датчики iBeacon и отслеживали перемещение погрузчиков между различными зонами. Каждая зона склада обозначается уникальным целым числом. Когда погрузчик перемещается из одной зоны в другую, система регистрирует номер новой зоны, в которую он въехал. В результате движение каждого погрузчика представлено в виде последовательности чисел. Инженеры завода считают, что наиболее эффективный путь, который может совершить погрузчик, — это поездка из некоторой начальной зоны в конечную, а затем возвращение по тому же маршруту обратно. Такая последовательность перемещений формирует так называемый «идеальный маршрут». Вам необходимо найти длину самого длинного «идеального маршрута» в записи перемещений погрузчика.

Формат ввода
Первая строка содержит одно целое число n (1 ≤ n ≤ 10^4) — количество записей о перемещениях погрузчика.

Вторая строка содержит n целых чисел a₁, a₂, ..., aₙ (1 ≤ aᵢ ≤ 10^9) — последовательность зон, через которые проехал погрузчик.

Формат вывода
Выведите одно целое число — длину самого длинного «идеального маршрута» в записи перемещений. Если такого маршрута не существует, выведите 0.

Пример 1
Ввод	Вывод
7
1 2 3 4 3 2 1
7
Пример 2
Ввод	Вывод
5
1 2 3 4 5
0
Пример 3
Ввод	Вывод
10
1 2 3 4 5 5 4 3 2 1
10
Пример 4
Ввод	Вывод
6
1 2 3 1 2 3
0
Пример 5
Ввод	Вывод
3
1 1 2
2
* */


public class Solution {

    private static int expandAroundCenter(List<Integer> route, int left, int right) {
        int n = route.size();

        if (left != right && !route.get(left).equals(route.get(right))) {
            return 0;
        }

        while (left >= 0 && right < n && route.get(left).equals(route.get(right))) {
            left--;
            right++;
        }

        return right - left - 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        String[] row = reader.readLine().split(" ");
        List<Integer> route = new ArrayList<>();
        for (String s : row) {
            route.add(Integer.parseInt(s));
        }

        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            int len = expandAroundCenter(route, i, i);
            if (len > 1) maxLen = Math.max(maxLen, len);

            if (i < n - 1) {
                len = expandAroundCenter(route, i, i + 1);
                maxLen = Math.max(maxLen, len);
            }
        }


        writer.write(String.valueOf(maxLen));

        reader.close();
        writer.close();
    }

}
