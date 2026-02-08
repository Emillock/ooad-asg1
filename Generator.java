/*
 * Name: Emil Mirzazada
 * Project: Random Number Generator and Analysis
 * Course: Object Oriented Analysis & Design
 * Date: 2026-02-08
 *
 * Description:
 * This program generates random numbers using three different built-in Java random number generators, including java.util.Random, Math.random(), java.util.concurrent.ThreadLocalRandom,
 * and analyzes them using descriptive statistics.
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Generator class is a public class and has methods for:
 * Populating ArrayList with numbers generated using one of the specified random generation methods, including java.util.Random, Math.random(), java.util.concurrent.ThreadLocalRandom.
 * Computing statistics, such as mean, standard deviation, maximum, minimum, from the given ArrayList of randomly generated numbers.
 * Displaying the statistics in terminal using custom tabular structure.
 * Executing generation of ArrayLists, computation of statistics and display of statistics in form of table for all 3 generation methods.
 */
public class Generator {

    /**
     * The following 3 attributes are constants that map each generation method to a number.
     * They are accessible only within this class due to the "private" keyword.
     */
    private static final int RNG_RANDOM = 0;
    private static final int RNG_MATH_RANDOM = 1;
    private static final int RNG_THREAD_LOCAL = 2;

    /**
     * The array of Strings that contains names of generation methods.
     * They are accessible only within this class due to the "private" keyword.
     */
    private static final String[] generatorNames = {
            "java.util.Random",
            "Math.random()",
            "ThreadLocalRandom"
    };

    /**
     * Constructor of the class.
     * As there are no attributes that should be specified during instantiation, the constructor is empty.
     */
    public Generator() {}

    /**
     * This method generates and returns a list of random numbers using one of the specified random generation methods.
     * Can be called outside the class as it is a public method.
     */
    public ArrayList<Double> populate(int n, int randNumGen) {

        /**
         * An empty ArrayList object is created for storing randomly generated Doubles.
         */
        ArrayList<Double> list = new ArrayList<>(n);

        switch (randNumGen) {

            case RNG_RANDOM: // java.util.Random
                Random rng = new Random();
                for (int i = 0; i < n; i++) {
                    list.add(rng.nextDouble());
                }
                break;

            case RNG_MATH_RANDOM: // Math.random()
                for (int i = 0; i < n; i++) {
                    list.add(Math.random());
                }
                break;

            case RNG_THREAD_LOCAL: // java.util.concurrent.ThreadLocalRandom
                for (int i = 0; i < n; i++) {
                    list.add(ThreadLocalRandom.current().nextDouble());
                }
                break;

            default:
                throw new IllegalArgumentException("Invalid random generator type.");
        }

        return list;
    }

    /**
     * Computes descriptive statistics for a list of values.
     * Statistics returned in order:
     * [n (sample size), mean, sample standard deviation, min, max]
     *
     * Uses Welford’s online algorithm that is good for processing data points one by one, without needing to store the entire dataset in memory (here was used just because of curiosity).
     * Found it on Wiki: https://en.wikipedia.org/wiki/Algorithms_for_calculating_variance
     * The following Java implementation of algorithm was used and modified: https://gist.github.com/tfmorris/0f3878a6fa1c91dc6787aee06f55cac8
     */
    public ArrayList<Double> statistics(ArrayList<Double> randomValues) {

        int n = randomValues.size();
        double mean = 0.0;
        double M2 = 0.0;
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;

        int cnt = 0;

        for (double x : randomValues) {
            cnt++;
            double delta = x - mean;
            mean += delta / cnt;
            M2 += delta * (x - mean);

            if (x < min) min = x;
            if (x > max) max = x;
        }

        double variance = (n > 1) ? M2 / (n - 1) : 0.0;
        double stddev = Math.sqrt(variance);

        ArrayList<Double> results = new ArrayList<>(5);
        results.add((double) n);
        results.add(mean);
        results.add(stddev);
        results.add(min);
        results.add(max);

        return results;
    }

    /**
     * Displays results in a formatted table.
     * This method is public so is callable from other classes.
     */
    public void display(ArrayList<Double> results, boolean headerOn) {

        if (headerOn) {
            System.out.printf("%-20s %8s %15s %15s %15s %15s%n",
                    "Generator", "n", "mean", "stddev", "min", "max");
            System.out.println("-----------------------------------------------------------------------------------------------");
        }

        System.out.printf("%-20s %8d %15.6f %15.6f %15.6f %15.6f%n",
                generatorNames[results.get(0).intValue()],
                results.get(1).intValue(),
                results.get(2),
                results.get(3),
                results.get(4),
                results.get(5));
    }

    /**
     * Executes the full experiment.
     * Calls populate(), statistics(), and display() for each of the methods and sample sizes.
     */
    public void execute() {

        int[] nValues = {10, 1000, 100000};


        boolean headerPrinted = false;

        for (int g = 0; g < generatorNames.length; g++) {

            for (int n : nValues) {
                ArrayList<Double> values = populate(n, g);
                ArrayList<Double> stats = statistics(values);
                stats.add(0, (double) g);
                display(stats, !headerPrinted);
                headerPrinted = true;
            }
            System.out.println("-----------------------------------------------------------------------------------------------");
        }
    }

    /**
     * Main function that starts execution.
     */
    public static void main(String[] args) {

        Generator gen = new Generator();
        gen.execute();
    }
}
