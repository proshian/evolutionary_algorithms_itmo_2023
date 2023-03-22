package queens;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.Random;

public class QueensInversionMutation extends QueensMutation implements EvolutionaryOperator<QueensSolution> {

    public QueensInversionMutation(){
        super();
    }

    public QueensInversionMutation(double probThreshold){
        super(probThreshold);
    }

    protected void applyForOneSolution(QueensSolution solution, Random random) {
        IndexPair iPair = IndexPair.getRandomOrderedIndexPair( solution.getDimension(), random);

        solution.invertRegion(iPair.i, iPair.j);

        solution.checkAllRowIndexesPresent();
    }
}
