package lab3;

import static org.junit.jupiter.api.Assertions.*;
class TspSolutionTest {
    public void watchReversed() {
        TspSolution s = new TspSolution(9);
//        System.out.println("before invertRegion: " + row_indexes);
        s.invertRegion(1, 5);
//        System.out.println("after invertRegion: " + row_indexes);
    }
}