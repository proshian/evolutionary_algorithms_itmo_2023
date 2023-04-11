package lab3;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

import java.util.Random;

public class TspFactory extends AbstractCandidateFactory<double[]> {


    private double getRandomDoubleInRange(Random random, int min_possible_val, int max_possible_val) {
        return random.nextDouble() * (max_possible_val - min_possible_val) + min_possible_val;
    }


    public double[] generateRandomCandidate(Random random) {
        int dimension = 2;
        double[] solution = new double[dimension];

        int x1_min = -5;
        int x1_max = 10;
        int x2_min = 0;
        int x2_max = 15;

        solution[0] = getRandomDoubleInRange(random, x1_min, x1_max);
        solution[1] = getRandomDoubleInRange(random, x2_min, x2_max);

        return solution;
    }

}

