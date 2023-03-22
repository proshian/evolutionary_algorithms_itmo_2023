package queens;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

import java.util.List;

public class QueensFitnessFunction implements FitnessEvaluator<QueensSolution> {

    public QueensFitnessFunction() {
        callsNum = 0;
    }

    public int getCallsNum() {
        return callsNum;
    }

    private int callsNum;

    public double getFitness(QueensSolution solution, List<? extends QueensSolution> list) {
        // For element of a solution:
        // if they are on the same diagonal, they beat each other.
        // Note that they cannot be on same column or row because of design of solution!
        // There are two types of diagonals: parallel to main and parallel to secondary.
        //
        // We should get all possible pairs of indexes from 0 to N = dimension.
        // if two queens are on  same diagonal, sumOfBeats += 1
        callsNum +=1;

        double beatenQueensNum = 0;

        int dimension = solution.getDimension();
        for (int colInd = 0; colInd < dimension; colInd++) {
            int mainDiagonalIndex = colInd - solution.getRowIndex(colInd);
            int secondaryDiagonalIndex = colInd + solution.getRowIndex(colInd);

            for (int colInd2 = 0; colInd2 < dimension; colInd2++) {
                if (colInd2 == colInd) {
                    continue;
                }

                int mainDiagonalIndex2 = colInd2 - solution.getRowIndex(colInd2);
                int secondaryDiagonalIndex2 = colInd2 + solution.getRowIndex(colInd2);

                if(mainDiagonalIndex2 == mainDiagonalIndex || secondaryDiagonalIndex2 == secondaryDiagonalIndex) {
                    beatenQueensNum += 1;
                    break;
                }
            }
        }
        return beatenQueensNum;
    }

    // If we aim to minimize fitness, isNatural should return false.
    public boolean isNatural() {
        return false;
    }
}
