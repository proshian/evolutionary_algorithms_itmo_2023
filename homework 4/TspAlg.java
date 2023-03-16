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
        // name of problem or path to input file
        String pathToTsp = "path\to\tsp";

        int populationSize = 100; // size of population
        int generations = 1000000; // number of generations


        Random random = new Random(); // random

        int cities_num = getDimension(pathToTsp);

        CandidateFactory<TspSolution> factory = new TspFactory(cities_num); // generation of solutions

        ArrayList<EvolutionaryOperator<TspSolution>> operators = new ArrayList<EvolutionaryOperator<TspSolution>>();
        operators.add(new TspCrossover()); // Crossover
        operators.add(new TspSwapMutation()); // Mutation
        EvolutionPipeline<TspSolution> pipeline = new EvolutionPipeline<TspSolution>(operators);

        SelectionStrategy<Object> selection = new RouletteWheelSelection(); // Selection operator

        FitnessEvaluator<TspSolution> evaluator = new TspFitnessFunction(pathToTsp); // Fitness function

        EvolutionEngine<TspSolution> algorithm = new SteadyStateEvolutionEngine<TspSolution>(
                factory, pipeline, evaluator, selection, populationSize, false, random);

        algorithm.addEvolutionObserver(new EvolutionObserver() {
            public void populationUpdate(PopulationData populationData) {
                double bestFit = populationData.getBestCandidateFitness();
                System.out.println("Generation " + populationData.getGenerationNumber() + ": " + bestFit);
                TspSolution best = (TspSolution)populationData.getBestCandidate();
                System.out.println("\tBest solution = " + best.toString());
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
