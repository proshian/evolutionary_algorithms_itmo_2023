package lab3;

import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import java.util.*;

public class TspCrossover extends AbstractCrossover<TspSolution> {
    protected TspCrossover(double crossOverProbability) {
        super(1, new Probability(crossOverProbability));
    }


    protected List<TspSolution> mate(TspSolution p1, TspSolution p2, int crossoverPointsNum, Random random) {
//        return dummy_mate(p1, p2, crossoverPointsNum, random);

        ArrayList children = new ArrayList();

        TspSolution child1 = TspSolution.orderedCrossover(p1, p2, random);
        TspSolution child2 = TspSolution.orderedCrossover(p2, p1, random);

        child1.checkAllRowIndexesPresent();
        child2.checkAllRowIndexesPresent();

        children.add(child1);
        children.add(child2);

        return children;
    }

    private List<TspSolution> dummy_mate(TspSolution p1, TspSolution p2, int crossoverPointsNum, Random random) {
        ArrayList children = new ArrayList();
        // ! не знаю, легально ли использовать именно p1 и p2 или необхдимо сделать их копии
        children.add(p1);
        children.add(p2);
        return children;
    }
}
