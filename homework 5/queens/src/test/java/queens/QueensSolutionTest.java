package queens;

        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;

        import java.util.Random;

        import static org.junit.jupiter.api.Assertions.*;

class QueensSolutionTest {

    private QueensSolution solution;
    private IndexPair iPair;

    private Random random;

    @BeforeEach
    void setUp() {
        int dimension = 8;
        random = new Random();
        solution = new QueensSolution(dimension);
        iPair = IndexPair.getRandomOrderedIndexPair(solution.getDimension(), random);
    }

    @Test
    void orderedCrossover() {

        int[] rowIndexes1 = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        int[] rowIndexes2 = {2, 6, 4, 1, 7, 0, 3, 8, 5};

        QueensSolution child = QueensSolution.orderedCrossover(
                new QueensSolution(rowIndexes1), new QueensSolution(rowIndexes2), 3, 6);

        assertArrayEquals(child.getRowIndexes(), new int[]{1, 7, 0, 3, 4, 5, 6, 8, 2});

        System.out.println("orderedCrossover passed");
    }


    @Test
    void swapIndexes() {
        System.out.println(solution);
        System.out.println(iPair.i + " " + iPair.j);
        solution.swapIndexes(iPair.i, iPair.j);
        System.out.println(solution);
    }

    @Test
    void insertIAfterJ() {
        System.out.println(solution);
        iPair = IndexPair.getRandomIndexPair(solution.getDimension(), random);
        System.out.println(iPair.i + " " + iPair.j);
        solution.insertIAfterJ(iPair.i, iPair.j);
        System.out.println(solution);
    }

    @Test
    void invertRegion() {
        System.out.println(solution);
        System.out.println(iPair.i + " " + iPair.j);
        solution.invertRegion(iPair.i, iPair.j);
        System.out.println(solution);
    }

    @Test
    void scrambleRegion() {
        System.out.println(solution);
        System.out.println(iPair.i + " " + iPair.j);
        solution.scrambleRegion(iPair.i, iPair.j);
        System.out.println(solution);
    }

}