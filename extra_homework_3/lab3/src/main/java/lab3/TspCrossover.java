package lab3;

import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import java.util.*;

public class TspCrossover extends AbstractCrossover<double[]> {
    protected TspCrossover(double crossOverProbability) {
        super(1, new Probability(crossOverProbability));
    }


    protected List<double[]> mate(double[] p1, double[] p2, int crossoverPointsNum, Random random) {
//        return dummy_mate(p1, p2, crossoverPointsNum, random);

        int dimension = 2;

        // your implementation:
        double[] child1 = new double[dimension];
        double[] child2 = new double[dimension];

        child1[0] = p1[0];
        child1[1] = p2[1];

        child2[0] = p2[0];
        child2[1] = p1[1];


        ArrayList children = new ArrayList();
        children.add(child1);
        children.add(child2);
        return children;
    }
}
