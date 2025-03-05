package core_java;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Comparator<String> comp = (o1, o2) -> o1.length() - o2.length();
        System.out.println(comp.compare("hello", "world"));
        Arrays.sort(new int[]{1, 2, 3}, Comparator.comparing(o -> o, Comparator.nullsFirst()
    }
}
