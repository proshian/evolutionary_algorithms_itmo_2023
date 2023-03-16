package lab3;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.List;
import java.util.Random;

abstract class TspMutation implements EvolutionaryOperator<TspSolution> {

    protected double probThreshold;

    public TspMutation(double probThreshold) {
        this.probThreshold = probThreshold;
    }

    public TspMutation() {
        this.probThreshold = 0.5D;
    }

    abstract protected void applyForOneSolution(TspSolution solution, Random random);

    public List<TspSolution> apply(List<TspSolution> population, Random random) {
        for (int i=0; i < population.size(); ++i) {
            if (random.nextDouble() < probThreshold) {
                applyForOneSolution(population.get(i), random);
            }
        }
        return population;
    }
}