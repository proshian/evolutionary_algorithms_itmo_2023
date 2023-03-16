package lab2;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.List;
import java.util.Random;

public class MyMutation implements EvolutionaryOperator<double[]> {
    public List<double[]> apply(List<double[]> population, Random random) {
        // initial population
        // need to change individuals, but not their number!

        // mutation_prob - probability for every gene that it will be mutated (resampled randomly)
        double mutation_prob = 0.005;

        for (int i = 0; i < population.size(); i++) {
            for (int j = 0; j < population.get(i).length; j++) {
                if (random.nextDouble() < mutation_prob){
                    population.get(i)[j] = random.nextDouble() * 10 - 5;
                }
            }
        }

        return population;
    }
}
