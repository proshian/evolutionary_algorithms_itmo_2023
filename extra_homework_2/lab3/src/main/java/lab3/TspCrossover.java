package lab3;

import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import java.util.*;

public class TspCrossover extends AbstractCrossover<TspPositionalSolution> {
    protected TspCrossover(double crossOverProbability) {
        super(1, new Probability(crossOverProbability));
    }




    protected List<TspPositionalSolution> mate(TspPositionalSolution p1, TspPositionalSolution p2, int crossoverPointsNum, Random random) {
//        return dummy_mate(p1, p2, crossoverPointsNum, random);

        // your implementation:
        TspPositionalSolution child1 = new TspPositionalSolution(p2);
        TspPositionalSolution child2 = new TspPositionalSolution(p1);

        int dimension = p1.getDimension();

        int lastIOfLeft = random.nextInt(dimension-1);

        for (int i = 0; i < lastIOfLeft; i++) {
            child1.set(i, p1.get(i));
            child2.set(i, p2.get(i));
        }


        ArrayList children = new ArrayList();
        children.add(child1);
        children.add(child2);
        return children;
    }

    private List<TspPositionalSolution> dummy_mate(TspPositionalSolution p1, TspPositionalSolution p2, int crossoverPointsNum, Random random) {
        ArrayList children = new ArrayList();
        // ! не знаю, легально ли использовать именно p1 и p2 или необхдимо сделать их копии
        children.add(p1);
        children.add(p2);
        return children;
    }
}
