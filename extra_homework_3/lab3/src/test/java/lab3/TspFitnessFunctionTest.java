package lab3;

import org.junit.jupiter.api.Test;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TspFitnessFunctionTest {

    @Test
    void getFitness() {
        FitnessEvaluator<double[]> evaluator = new TspFitnessFunction(); // Fitness function

        ArrayList<double[]> population = new ArrayList<double[]>();

        double[] el1 = new double[]{Math.PI, 2.275};
        //double[] el1 = new double[]{-3.173385,12.294022};

        population.add(el1);

        System.out.println(evaluator.getFitness(el1, population));
    }
}