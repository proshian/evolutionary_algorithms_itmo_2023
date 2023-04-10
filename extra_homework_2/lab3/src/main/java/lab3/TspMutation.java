package lab3;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract class TspMutation implements EvolutionaryOperator<TspPositionalSolution> {

    protected double probGlobal;
    protected double localProbCoef;


    public TspMutation(double probGlobal, double localProbCoef) {
        this.probGlobal = probGlobal;
        this.localProbCoef = localProbCoef;
    }

    public TspMutation(double probGlobal) {
        this.probGlobal = probGlobal;
        localProbCoef = 1.0D;
    }

    public TspMutation() {
        probGlobal = 0.5D; localProbCoef = 1.0D;
    }

    protected void applyForOneSolution(TspPositionalSolution solution, Random random) {
        int dimension = solution.getDimension();
        double localProbThreshold = localProbCoef/dimension;

        for(int i = 0; i < dimension; i++) {
            if (random.nextDouble() < localProbThreshold) {
                solution.set(i, random.nextInt(dimension - i));
            }
        }
    }

    public List<TspPositionalSolution> apply(List<TspPositionalSolution> population, Random random) {
        ArrayList<TspPositionalSolution> newPopulation = new ArrayList<TspPositionalSolution>(population.size());

        for (int i=0; i < population.size(); ++i) {

            TspPositionalSolution solution =  population.get(i);
            if (random.nextDouble() < probGlobal) {
                TspPositionalSolution mutatedSolution = new TspPositionalSolution(solution);
                applyForOneSolution(mutatedSolution, random);
                newPopulation.add(mutatedSolution);
            }
            else {
                newPopulation.add(solution);
            }
        }
        return population;
    }
}