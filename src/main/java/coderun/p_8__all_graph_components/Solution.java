package coderun.p_8__all_graph_components;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
* 8. Компоненты связности

Средняя
Дан неориентированный невзвешенный граф, состоящий из N вершин и M ребер. Необходимо посчитать количество его компонент связности и вывести их.

Напомним:
Компонента связности в неориентированном графе - это подмножество ершин, таких что все вершины достижимы друг из друга.

Формат ввода
Во входном файле записано два числа N и M (0 < N ≤ 100000, 0 ≤ M ≤ 100000). В следующих M строках записаны по два числа i и j (1 ≤ i, j ≤ N), которые означают, что вершины i и j соединены ребром.

Формат вывода
В первой строчке выходного файла выведите количество компонент связности. Далее выведите сами компоненты связности в следующем формате: в первой строке количество вершин в компоненте, во второй - сами вершины в произвольном порядке.

Ограничения
Ограничение времени
2 с

Ограничение памяти
256 МБ

Пример 1
Ввод
6 4
3 1
1 2
5 4
2 3
Вывод
3
3
1 2 3
2
4 5
1
6
Пример 2
Ввод
6 4
4 2
1 4
6 4
3 6
Вывод
2
5
1 2 3 4 6
1
5
* */

public class Solution {

    public static void dfs(int vertex, TreeSet<Integer> component, HashMap<Integer, HashSet<Integer>> adjM, boolean[] visited) {
        component.add(vertex);
        visited[vertex] = true;

        if (!adjM.containsKey(vertex)) {
            return;
        }

        for (int neighbor : adjM.get(vertex)) {
            if (!visited[neighbor]) {
                dfs(neighbor, component, adjM, visited);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // Input
        String[] row = reader.readLine().split(" ");
        int n = Integer.parseInt(row[0]); // number of vertices
        int m = Integer.parseInt(row[1]); // number of edges

        var adjM = new HashMap<Integer, HashSet<Integer>>(); // 1-based
        boolean[] visited = new boolean[n+1]; // 1-based
        List<TreeSet<Integer>> components = new ArrayList<>();

        // Read edges
        for (int i = 0; i < m; i++) {
            row = reader.readLine().split(" ");
            int a = Integer.parseInt(row[0]);
            int b = Integer.parseInt(row[1]);
            adjM.computeIfAbsent(a, k -> new HashSet<Integer>()).add(b);
            adjM.computeIfAbsent(b, k -> new HashSet<Integer>()).add(a); // edges are bidirectional
        }

        // Find all components of the graph
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                TreeSet<Integer> component = new TreeSet<>();
                dfs(i, component, adjM, visited);
                components.add(component);
            }
        }

        // Output
        writer.write(String.valueOf(components.size()));
        writer.newLine();

        for (TreeSet<Integer> component : components) {
            writer.write(String.valueOf(component.size()));
            writer.newLine();
            writer.write(component.stream().map(String::valueOf).collect(Collectors.joining(" ")));
            writer.newLine();
        }

        reader.close();
        writer.close();
    }

}
