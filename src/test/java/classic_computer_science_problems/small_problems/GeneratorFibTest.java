package classic_computer_science_problems.small_problems;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorFibTest {

    @Test
    void stream() {
        GeneratorFib generatorFib = new GeneratorFib();
        assertEquals(0, generatorFib.stream().findFirst().getAsInt());
        assertEquals(1, generatorFib.stream().findFirst().getAsInt());
        assertEquals(1, generatorFib.stream().findFirst().getAsInt());
        assertEquals(2, generatorFib.stream().findFirst().getAsInt());
        assertEquals(3, generatorFib.stream().findFirst().getAsInt());
        assertEquals(5, generatorFib.stream().findFirst().getAsInt());
        assertEquals(8, generatorFib.stream().findFirst().getAsInt());

    }
}
