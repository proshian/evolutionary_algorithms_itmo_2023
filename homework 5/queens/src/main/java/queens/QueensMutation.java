package queens;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract class QueensMutation implements EvolutionaryOperator<QueensSolution> {

    protected double probThreshold;

    public QueensMutation(double probThreshold) {
        this.probThreshold = probThreshold;
    }

    public QueensMutation() {
        this.probThreshold = 0.5D;
    }

    abstract protected void applyForOneSolution(QueensSolution solution, Random random);

    public List<QueensSolution> apply(List<QueensSolution> population, Random random) {
        ArrayList<QueensSolution> newPopulation = new ArrayList<QueensSolution>(population.size());

        for (int i=0; i < population.size(); ++i) {
            QueensSolution solution =  population.get(i);
            if (random.nextDouble() < probThreshold) {
                QueensSolution mutatedSolution = new QueensSolution(solution);
                mutatedSolution.checkAllRowIndexesPresent();
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