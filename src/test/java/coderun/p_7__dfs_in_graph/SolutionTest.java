package coderun.p_7__dfs_in_graph;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testDfsMethod() {
        // Create a simple graph
        HashMap<Integer, HashSet<Integer>> adjM = new HashMap<>();
        adjM.put(1, new HashSet<>(Arrays.asList(2, 3)));
        adjM.put(2, new HashSet<>(Arrays.asList(1, 3, 4)));
        adjM.put(3, new HashSet<>(Arrays.asList(1, 2, 4)));
        adjM.put(4, new HashSet<>(Arrays.asList(2, 3)));

        boolean[] visited = new boolean[5]; // 1-based indexing
        List<Integer> component = new ArrayList<>();

        Solution.dfs(1, component, adjM, visited);

        // Check if all vertices are visited
        assertEquals(4, component.size());
        assertTrue(component.containsAll(Arrays.asList(1, 2, 3, 4)));
    }

    @Test
    public void testDfsWithDisconnectedGraph() {
        // Create a graph with disconnected components
        HashMap<Integer, HashSet<Integer>> adjM = new HashMap<>();
        adjM.put(1, new HashSet<>(Arrays.asList(2)));
        adjM.put(2, new HashSet<>(Arrays.asList(1)));
        adjM.put(3, new HashSet<>(Arrays.asList(4)));
        adjM.put(4, new HashSet<>(Arrays.asList(3)));

        boolean[] visited = new boolean[5]; // 1-based indexing
        List<Integer> component = new ArrayList<>();

        Solution.dfs(1, component, adjM, visited);

        // Check if only vertices 1 and 2 are visited
        assertEquals(2, component.size());
        assertTrue(component.containsAll(Arrays.asList(1, 2)));
        assertFalse(component.contains(3));
        assertFalse(component.contains(4));
    }
    @Test
    public void testMainMethodWithExampleInput() throws IOException {
        // Prepare input
        String input = "4 5\n2 2\n3 4\n2 3\n1 3\n2 4";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Run the main method
        Solution.main(new String[]{});

        // Check output
        String expectedOutput = "4\n1 2 3 4";
        assertEquals(expectedOutput, outContent.toString().trim().replace("\r", ""));
    }

    @Test
    public void testMainMethodWithSingleVertex() throws IOException {
        // Prepare input with a single vertex and no edges
        String input = "1 0";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Run the main method
        Solution.main(new String[]{});

        // Check output - should only contain vertex 1
        String expectedOutput = "1\n1";
        assertEquals(expectedOutput, outContent.toString().trim().replace("\r", ""));
    }

    @Test
    public void testMainMethodWithDisconnectedComponents() throws IOException {
        // Prepare input with disconnected components
        String input = "5 2\n1 2\n3 4";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Run the main method
        Solution.main(new String[]{});

        // Check output - should only contain vertices 1 and 2
        String expectedOutput = "2\n1 2";
        assertEquals(expectedOutput, outContent.toString().trim().replace("\r", ""));
    }

    @Test
    public void testMainMethodWithIsolatedVertex() throws IOException {
        // Prepare input where vertex 1 is isolated
        String input = "3 1\n2 3";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Run the main method
        Solution.main(new String[]{});

        // Check output - should only contain vertex 1
        String expectedOutput = "1\n1";
        assertEquals(expectedOutput, outContent.toString().trim().replace("\r", ""));
    }
}
