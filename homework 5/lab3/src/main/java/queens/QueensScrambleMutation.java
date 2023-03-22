package queens;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.Random;

public class QueensScrambleMutation extends QueensMutation implements EvolutionaryOperator<QueensSolution> {

    public QueensScrambleMutation(){
        super();
    }

    public QueensScrambleMutation(double probThreshold){
        super(probThreshold);
    }

    protected void applyForOneSolution(QueensSolution solution, Random random) {
        IndexPair iPair = IndexPair.getRandomOrderedIndexPair(solution.getDimension(), random);

        solution.scrambleRegion(iPair.i, iPair.j);
    }
}