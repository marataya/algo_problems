package coderun.p_7__dfs_in_graph;


import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
*
7. Поиск в глубину
Средняя
Дан неориентированный граф, возможно с петлями и кратными ребрами. Необходимо найти компоненту связности, содержащую вершину с номером 1.


* Формат ввода
* В первой строке записаны два целых числа N (1 ≤ N ≤ 10) и M (0 ≤ M ≤ 10) — количество вершин и ребер в графе. В последующих M строках перечислены ребра — пары чисел, определяющие номера вершин, которые соединяют ребра. Вершины нумеруются с единицы.

* Формат вывода
В первой строке выведите число K — количество вершин в компоненте связности. Во второй строке выведите K целых чисел — вершины компоненты связности, перечисленные в порядке возрастания номеров.

Примечание
Петля в графе — это ребро, которое соединяет вершину с самой собой. Кратные рёбра в графе — это рёбра, которые соединяют одну и ту же пару вершин.
* Компонента связности в неориентированном графе — это подмножество вершин таких, что все вершины достижимы друг из друга.

Ограничения
Ограничение времени
2 с
Ограничение памяти
256 МБ

Пример 1
Ввод
4 5
2 2
3 4
2 3
1 3
2 4

Вывод
4
1 2 3 4
* */
public class Solution {
    public static void dfs(int startVertex, List component, HashMap<Integer, HashSet<Integer>> adjM, boolean[] visited) {
        if (!adjM.containsKey(startVertex)) {
            return;
        }

        visited[startVertex] = true;
        component.add(startVertex);
        for (int neighbor : adjM.get(startVertex)) {
            if (!visited[neighbor]) {
                dfs(neighbor, component, adjM, visited);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // Read input
        String[] row = reader.readLine().split(" ");
        int n = Integer.parseInt(row[0]); // number of vertices
        int m = Integer.parseInt(row[1]); // number of edges

        // DFS for vertex #1
        var adjM = new HashMap<Integer, HashSet<Integer>>(); // 1-based
        boolean[] visited = new boolean[n+1]; // 1-based
        var component = new ArrayList<Integer>();

        for (int i = 0; i < m; i++) {
            row = reader.readLine().split(" ");
            int a = Integer.parseInt(row[0]);
            int b = Integer.parseInt(row[1]);
            adjM.computeIfAbsent(a, k -> new HashSet<Integer>()).add(b);
            adjM.computeIfAbsent(b, k -> new HashSet<Integer>()).add(a); // edges are bidirectional
        }

        // recursive call of DFS
        dfs(1, component, adjM, visited);

        if (component.size() == 0) {
            writer.write("1");
            writer.newLine();
            writer.write("1");
        } else {
            writer.write(String.valueOf(component.size()));
            writer.newLine();
            writer.write(component.stream().sorted().map(String::valueOf).collect(Collectors.joining(" ")));
        }

        reader.close();
        writer.close();
    }
}
