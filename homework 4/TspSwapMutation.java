package lab3;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TspSwapMutation implements EvolutionaryOperator<TspSolution> {
    public List<TspSolution> apply(List<TspSolution> population, Random random) {
        // your implementation:

        for (int i=0; i < population.size(); ++i) {
            swapMutationInplace(population.get(i), random);
        }

        return population;
    }

    private void swapMutationInplace(TspSolution solution, Random random) {
        ArrayList<Integer> route = solution.getRoute();

        int route_len = route.size();

        int i = random.nextInt(route_len);

        int j = random.nextInt(route_len);

        while (j == i) {
            j = random.nextInt(route_len);
        }

        solution.swapIndexes(i, j);
    }
}
