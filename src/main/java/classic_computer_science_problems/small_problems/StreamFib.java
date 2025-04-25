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
        final int[] counter = {0};

        StreamFib sf = new StreamFib();
        sf.stream().limit(41).forEachOrdered((se) -> {
            System.out.printf("%d\t\t", se);
            counter[0]++;
            if (counter[0] % 10 == 0) {
                System.out.println();
            }
        });
    }
}
