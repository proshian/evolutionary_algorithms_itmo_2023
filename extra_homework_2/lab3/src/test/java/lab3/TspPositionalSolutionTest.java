package lab3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TspPositionalSolutionTest {

    @Test
    void getPathRepresentation() {
        int[] route = {0, 0, 1, 0, 3, 0, 2, 0, 0};

        TspPositionalSolution solution = new TspPositionalSolution(route);

        assertArrayEquals(solution.getPathRepresentation(), new int[]{0, 1, 3, 2, 7, 4, 8, 5, 6});

        System.out.println("getPathRepresentation passed");
    }
}