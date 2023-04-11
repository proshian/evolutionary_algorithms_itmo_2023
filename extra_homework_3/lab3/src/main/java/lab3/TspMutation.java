package lab3;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class TspMutation implements EvolutionaryOperator<double[]> {

    protected double globalProbThresh;
    protected double localProbThresh1;
    protected double localProbThresh2;


    public TspMutation(double localProbThresh1, double localProbThresh2, double globalProbThresh) {
        this.localProbThresh1 = localProbThresh1;
        this.localProbThresh2 = localProbThresh2;
        this.globalProbThresh = globalProbThresh;
    }

    private double getRandomDoubleInRange(Random random, int min_possible_val, int max_possible_val) {
        return random.nextDouble() * (max_possible_val - min_possible_val) + min_possible_val;
    }

    protected void applyForOneSolution(double[] solution, Random random) {
        int x1_min = -5;
        int x1_max = 10;
        int x2_min = 0;
        int x2_max = 15;

        if (random.nextDouble() < localProbThresh1) {
            solution[0] = getRandomDoubleInRange(random, x1_min, x1_max);
        }

        if (random.nextDouble() < localProbThresh2) {
            solution[1] = getRandomDoubleInRange(random, x2_min, x2_max);
        }
    }

    public List<double[]> apply(List<double[]> population, Random random) {
        ArrayList<double[]> newPopulation = new ArrayList<double[]>(population.size());

        for (int i=0; i < population.size(); ++i) {

            double[] solution =  population.get(i).clone();
            if (random.nextDouble() < globalProbThresh) {
                applyForOneSolution(solution, random);
            }
            newPopulation.add(solution);
        }
        return population;
    }
}