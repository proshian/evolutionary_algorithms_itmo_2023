package lab3;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

import java.util.ArrayList;
import java.util.List;

public class TspFitnessFunction implements FitnessEvaluator<double[]> {

    double bestFit = 9999;

    public double getFitness(double[] solution, List<? extends double[]> list) {
        double a = 1.0D;
        double b = 5.1/(4*Math.pow(Math.PI, 2));
        double c = 5/Math.PI;
        double d = 6;
        double e = 10;
        double f = 1/(8*Math.PI);

        double x1 = solution[0];
        double x2 = solution[1];

        double val = a * Math.pow(x2 - b * Math.pow(x1, 2) + c * x1 - d, 2) + e * (1-f) * Math.cos(x1) + e;

        if (val<bestFit){
            bestFit = val;
        }

        return val;
    }

    public boolean isNatural() {
        return false;
    }
}
