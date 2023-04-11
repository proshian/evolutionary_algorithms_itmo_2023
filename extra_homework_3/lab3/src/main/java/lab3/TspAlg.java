package lab3;

import org.uncommons.watchmaker.framework.*;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.termination.GenerationCount;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class TspAlg {

    public static void main(String[] args) {
        // name of problem or path to input file

        int populationSize = 1000 ; // size of population
        int generations = 10000; //1000000; // number of generations


        Random random = new Random(); // random

        CandidateFactory<double[]> factory = new TspFactory(); // generation of solutions

        ArrayList<EvolutionaryOperator<double[]>> operators = new ArrayList<EvolutionaryOperator<double[]>>();
        operators.add(new TspCrossover(0.5)); // Crossover
        operators.add(new TspMutation(0.5, 0.5, 0.01)); // Crossover

        EvolutionPipeline<double[]> pipeline = new EvolutionPipeline<double[]>(operators);

        SelectionStrategy<Object> selection = new RouletteWheelSelection(); // Selection operator

        FitnessEvaluator<double[]> evaluator = new TspFitnessFunction(); // Fitness function

        EvolutionEngine<double[]> algorithm = new SteadyStateEvolutionEngine<double[]>(
                factory, pipeline, evaluator, selection, populationSize, false, random);




        algorithm.addEvolutionObserver(new EvolutionObserver() {
            int generationOfBestFitness = 0;
            double prevBestFit = 999999999;
            public void populationUpdate(PopulationData populationData) {
                double bestFit = populationData.getBestCandidateFitness();
                System.out.println("Generation " + populationData.getGenerationNumber() + ": " + bestFit);
                double[] best = (double[])populationData.getBestCandidate();

                String message = String.format("\tBest solution = %f %f", best[0], best[1]);

                System.out.println(message);

                if (prevBestFit > bestFit) {
                    generationOfBestFitness = populationData.getGenerationNumber();
                    prevBestFit = bestFit;
                }
                System.out.println("\tGeneration of best solution = " + generationOfBestFitness);
            }
        });

        TerminationCondition terminate = new GenerationCount(generations);
        algorithm.evolve(populationSize, 1, terminate);
    }
}
