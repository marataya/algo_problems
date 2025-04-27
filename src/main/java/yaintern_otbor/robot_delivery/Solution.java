package coderun.robot_delivery;

/*
* Робот на севере
Ограничение времени	4 секунды
Ограничение памяти	256 Мб

* В одном северном городе погодные условия насколько суровы, что иногда выйти на улицу даже в магазин у дома практически невозможно, поэтому было решено запустить специальных роботов, которые доставляли бы жителям продукты во время метелей, ураганов и просто экстремальных температур. Каждый робот начинает свой путь из определённой точки загрузки, развозит продукты по нескольким адресам и возвращается на базу для перезарядки, после чего снова отправляется на выполнение задач. О каждом перемещении между адресами робот отчитывается отправкой сообщения на сервер, однако из-за проблем с сетью эти посылки приходят в вразнобой и на сервере теряется правильная очерёдность...
По полученным данным вам необходимо определить, по какому адресу робот был загружен для выполнения доставки и где закончил свой путь.

* Формат ввода
В первой строке содержится число n nn — количество перемещений робота. В следующих n nn строках содержится информация о каждом перемещении.

Любое перемещение описывается парой адресов: откуда и куда, — сами же адреса состоят из названия улицы (ai) и номера дома (hi), разделённых пробелом. Название улицы (ai) состоит из одного слова, содержащего не более 16 символов, и включает только буквы английского алфавита (как в верхнем, так и в нижнем регистре).

1 ≤ hi ≤2000, 1 ≤ n ≤ 500000
*
* Формат вывода
Пара адресов: точка старта и точка конца маршрута, разделённые пробелом.
Если точно определить необходимую информацию невозможно, выведите -−1.

Пример 1
Ввод	Вывод
1
Lenina 10 Storozhevaya 25
Lenina 10 Storozhevaya 25
Пример 2
Ввод	Вывод
2
Frunze 19 Dybenko 22
Dybenko 22 Frunze 19
-1
Пример 3
Ввод	Вывод
3
Dozhdevaya 15 LvaTolstogo 16
Lenina 2 Dozhdevaya 15
LvaTolstogo 16 Kosmonavtov 4
Lenina 2 Kosmonavtov 4
* */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String[][] moves = new String[n][4];
        for (int i = 0; i < n; i++) {
            moves[i] = reader.readLine().split(" ");
        }

        if (n == 0) {
            System.out.println("-1");
            return;
        }

        Map<String, Integer> startCounts = new HashMap<>();
        Map<String, Integer> endCounts = new HashMap<>();
        Set<String> allLocations = new HashSet<>();

        for (String[] move : moves) {
            String startLocation = move[0] + " " + move[1];
            String endLocation = move[2] + " " + move[3];

            startCounts.put(startLocation, startCounts.getOrDefault(startLocation, 0) + 1);
            endCounts.put(endLocation, endCounts.getOrDefault(endLocation, 0) + 1);
            allLocations.add(startLocation);
            allLocations.add(endLocation);
        }

        String startCandidate = null;
        String endCandidate = null;
        int startDiff = 0;
        int endDiff = 0;

        for (String location : allLocations) {
            int startCount = startCounts.getOrDefault(location, 0);
            int endCount = endCounts.getOrDefault(location, 0);
            int diff = startCount - endCount;

            if (diff > 0) {
                startDiff++;
                startCandidate = location;
            } else if (diff < 0) {
                endDiff++;
                endCandidate = location;
            }
        }

        if (startDiff == 1 && endDiff == 1) {
            System.out.println(startCandidate + " " + endCandidate);
        } else if (n == 1 && allLocations.size() == 1) {
            String startEnd = allLocations.iterator().next();
            System.out.println(startEnd + " " + startEnd);
        } else {
            System.out.println("-1");
        }
    }
}
