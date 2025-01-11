package classic_computer_science_problems.small_problems;

import java.util.stream.IntStream;

public class GeneratorFib {
    private int last = 0, next = 1;

    public IntStream stream() {
        return IntStream.generate(() -> {
            int oldLast = last;
            last = next;
            next = oldLast + next;
            return oldLast;
        });
    }

    public static void main(String[] args) {
        var gf = new GeneratorFib();
        System.out.println(gf.stream().findFirst().getAsInt());
        System.out.println(gf.stream().findFirst().getAsInt());
        System.out.println(gf.stream().findFirst().getAsInt());
        System.out.println(gf.stream().findFirst().getAsInt());
        System.out.println(gf.stream().findFirst().getAsInt());
    }
}
