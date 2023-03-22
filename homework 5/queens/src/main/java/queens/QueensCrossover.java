package queens;

import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import java.util.*;

public class QueensCrossover extends AbstractCrossover<QueensSolution> {
    protected QueensCrossover(double crossOverProbability) {
        super(1, new Probability(crossOverProbability));
    }


    protected List<QueensSolution> mate(QueensSolution p1, QueensSolution p2, int crossoverPointsNum, Random random) {
//        return dummy_mate(p1, p2, crossoverPointsNum, random);

        ArrayList children = new ArrayList();

        QueensSolution child1 = QueensSolution.orderedCrossover(p1, p2, random);
        QueensSolution child2 = QueensSolution.orderedCrossover(p2, p1, random);

        child1.checkAllRowIndexesPresent();
        child2.checkAllRowIndexesPresent();

        children.add(child1);
        children.add(child2);

        return children;
    }

    private List<QueensSolution> dummy_mate(QueensSolution p1, QueensSolution p2, int crossoverPointsNum, Random random) {
        ArrayList children = new ArrayList();
        // ! не знаю, легально ли использовать именно p1 и p2 или необхдимо сделать их копии
        children.add(p1);
        children.add(p2);
        return children;
    }
}
