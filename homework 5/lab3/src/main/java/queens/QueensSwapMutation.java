package queens;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.Random;

public class QueensSwapMutation extends QueensMutation implements EvolutionaryOperator<QueensSolution> {

    public QueensSwapMutation(){
        super();
    }

    public QueensSwapMutation(double probThreshold){
        super(probThreshold);
    }

    protected void applyForOneSolution(QueensSolution solution, Random random) {
        IndexPair iPair = IndexPair.getRandomIndexPair(solution.getDimension(), random);

        solution.swapIndexes(iPair.i, iPair.j);

        // solution.checkAllRowIndexesPresent();
    }
}