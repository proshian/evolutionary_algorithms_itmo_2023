package queens;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

import java.util.Random;

public class QueensFactory extends AbstractCandidateFactory<QueensSolution> {

    private int dimension;

    public QueensFactory(int dimension) {
        this.dimension = dimension;
    }

    public QueensSolution generateRandomCandidate(Random random) {
        return new QueensSolution(dimension);
    }

}

