package lab3;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TspInsertMutation extends TspMutation implements EvolutionaryOperator<TspSolution> {

    public TspInsertMutation(){
        super();
    }

    public TspInsertMutation(double probThreshold){
        super(probThreshold);
    }

    protected void applyForOneSolution(TspSolution solution, Random random) {
        ArrayList<Integer> route = solution.getRoute();

        int route_len = route.size();

        int i = random.nextInt(route_len);

        int j = random.nextInt(route_len);

        while (j == i) {
            j = random.nextInt(route_len);
        }

        solution.insertIAfterJ(i, j);

//        solution.checkAllCitiesPresent();
    }
}
