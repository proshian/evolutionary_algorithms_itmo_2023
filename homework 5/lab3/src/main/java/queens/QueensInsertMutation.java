package queens;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.Random;

public class QueensInsertMutation extends QueensMutation implements EvolutionaryOperator<QueensSolution> {

    public QueensInsertMutation(){
        super();
    }

    public QueensInsertMutation(double probThreshold){
        super(probThreshold);
    }

    protected void applyForOneSolution(QueensSolution solution, Random random) {
        IndexPair iPair = IndexPair.getRandomIndexPair(solution.getDimension(), random);

        solution.insertIAfterJ(iPair.i, iPair.j);

//        solution.checkAllRowIndexesPresent();
    }
}