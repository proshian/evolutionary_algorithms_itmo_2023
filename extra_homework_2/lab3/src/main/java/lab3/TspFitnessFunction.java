package lab3;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

import java.util.ArrayList;
import java.util.List;

public class TspFitnessFunction implements FitnessEvaluator<TspPositionalSolution> {

    private DistanceGetter distanceGetter = null;

    public TspFitnessFunction(String pathToTsp) {
        distanceGetter = new DistanceGetter(pathToTsp);
    }

    public double getFitness(TspPositionalSolution solution, List<? extends TspPositionalSolution> list) {
        int[] route = solution.getPathRepresentation();

        double totalDistance = 0.0D;

        for(int cur_i = 1; cur_i < route.length; cur_i++){
            int prev_i = cur_i - 1;
            totalDistance += distanceGetter.getDistance(route[prev_i], route[cur_i]);
        }
        return totalDistance;
    }

    // If we aim to minimize fitness, isNatural should return false.
    public boolean isNatural() {
        return false;
    }
}
