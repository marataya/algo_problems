package core_java;

import java.util.Random;

public class RandomBase36String {
    public static void main(String[] args) {
        var random = new Random();

        long randomLong = random.nextLong();

        String base36Long = Long.toString(randomLong, 36);

        System.out.println("Random Base-36 string: " + base36Long);
    }
}
