package classic_computer_science_problems.small_problems;

import java.util.stream.IntStream;

public class StreamFib {
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
        StreamFib sf = new StreamFib();
        sf.stream().limit(41).forEachOrdered(System.out::println);
    }
}
