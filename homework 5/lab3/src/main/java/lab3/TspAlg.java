package lab3;

import org.uncommons.watchmaker.framework.*;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.termination.GenerationCount;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class TspAlg {
    public static void main(String[] args) {

        int populationSize = 10; // size of population
        int generations = 1000000; //1000000; // number of generations


        Random random = new Random(); // random

        int dimension = 8;

        CandidateFactory<TspSolution> factory = new TspFactory(dimension); // generation of solutions

        ArrayList<EvolutionaryOperator<TspSolution>> operators = new ArrayList<EvolutionaryOperator<TspSolution>>();
//        operators.add(new TspCrossover(1.0)); // Crossover
        // Mutations
        operators.add(new TspSwapMutation(0.3));
//        operators.add(new TspInsertMutation(0.5));
//        operators.add(new TspInversionMutation(0.25));
//        operators.add(new TspScrambleMutation(0.4));

        EvolutionPipeline<TspSolution> pipeline = new EvolutionPipeline<TspSolution>(operators);

        SelectionStrategy<Object> selection = new RouletteWheelSelection(); // Selection operator

        FitnessEvaluator<TspSolution> evaluator = new TspFitnessFunction(); // Fitness function

        EvolutionEngine<TspSolution> algorithm = new SteadyStateEvolutionEngine<TspSolution>(
                factory, pipeline, evaluator, selection, populationSize, false, random);


        algorithm.addEvolutionObserver(new EvolutionObserver() {
            int generationOfBestFitness = 0;
            double prevBestFit = 999999999;
            public void populationUpdate(PopulationData populationData) {
                double bestFit = populationData.getBestCandidateFitness();
                System.out.println("Generation " + populationData.getGenerationNumber() + ": " + bestFit);
                TspSolution best = (TspSolution)populationData.getBestCandidate();
                System.out.println("\tBest solution = " + best.toString());

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



    private static int getDimension(String pathToTsp) {
        int dimension = -1;

        FileInputStream stream = null;
        try {
            stream = new FileInputStream(pathToTsp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        try {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if (line.startsWith("DIMENSION")) {
                    dimension = Integer.parseInt(line.substring(line.lastIndexOf(" ")+1));
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dimension;
    }
}
