package queens;

import org.uncommons.watchmaker.framework.*;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
//import org.uncommons.watchmaker.framework.termination.GenerationCount;
import org.uncommons.watchmaker.framework.termination.TargetFitness;

import java.util.ArrayList;
import java.util.Random;

public class QueensAlg {
    public static void alg(String[] args) {

        int populationSize = 2000000  ; // size of population
        Random random = new Random(); // random
        int dimension = 32;

        CandidateFactory<QueensSolution> factory = new QueensFactory(dimension); // generation of solutions

        ArrayList<EvolutionaryOperator<QueensSolution>> operators = new ArrayList<EvolutionaryOperator<QueensSolution>>();
        operators.add(new QueensCrossover(1.0)); // Crossover
        // Mutations
        operators.add(new QueensSwapMutation(0.4));
        operators.add(new QueensInsertMutation(0.3));
        operators.add(new QueensInversionMutation(0.1));
        operators.add(new QueensScrambleMutation(0.1));

        EvolutionPipeline<QueensSolution> pipeline = new EvolutionPipeline<QueensSolution>(operators);

        SelectionStrategy<Object> selection = new RouletteWheelSelection(); // Selection operator

        FitnessEvaluator<QueensSolution> evaluator = new QueensFitnessFunction(); // Fitness function

        EvolutionEngine<QueensSolution> algorithm = new SteadyStateEvolutionEngine<QueensSolution>(
                factory, pipeline, evaluator, selection, populationSize, false, random);


        algorithm.addEvolutionObserver(new EvolutionObserver() {
            int generationOfBestFitness = 0;
            double prevBestFit = 999999999;

            int fitnessCallsNum = 0;

            public void populationUpdate(PopulationData populationData) {
                double bestFit = populationData.getBestCandidateFitness();
                System.out.println("Generation " + populationData.getGenerationNumber() + ": " + bestFit);
                QueensSolution best = (QueensSolution)populationData.getBestCandidate();
                System.out.println("\tBest solution = " + best.toString());

                if (prevBestFit > bestFit) {
                    generationOfBestFitness = populationData.getGenerationNumber();
                    prevBestFit = bestFit;
                }
                System.out.println("\tGeneration of best solution = " + generationOfBestFitness);

            }
        });

        TerminationCondition terminate = new TargetFitness(0.0D, false);
        // TerminationCondition terminate = new GenerationCount(generations);
        algorithm.evolve(populationSize, 1, terminate);
    }

    public static void main(String[] args) {
        for(int i = 0; i < 10;i++) {
            alg(new String[]{});
            System.out.println();
        }
    }
}
