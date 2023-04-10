package lab3;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

import java.util.Random;

public class TspFactory extends AbstractCandidateFactory<TspPositionalSolution> {

    private int cities_num;
    private Random random;

    public TspFactory(int cities_num) {
        this.cities_num = cities_num;
    }

    public TspPositionalSolution generateRandomCandidate(Random random) {
        TspPositionalSolution solution = new TspPositionalSolution(cities_num, random);
        //your implementation

        return solution;
    }

}

