package cli;

import metrics.Counters;
import sort.MergeSort;
import sort.QuickSort;
import sort.DeterministicSelect;
import sort.ClosestPair;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws Exception {
        Random rnd = new Random(12345);

        int[] sizes = {1000, 2000, 4000, 8000, 16000};
        int trials = 5;
        int warmup = 2;
        String outFile = "docs/results.csv";

        runAllExperiments(sizes, trials, warmup, outFile, rnd);
    }

    private static void runAllExperiments(int[] sizes, int trials, int warmup, String outFile, Random rnd) throws IOException {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(outFile))) {

            w.write("algo,n,trial,timeNs,comparisons,swaps,allocations,maxDepth\n");

            for (int n : sizes) {
                System.out.println("=== n = " + n + " ===");

                // ---- MergeSort ----
                System.out.println("Warmup MergeSort...");
                for (int i = 0; i < warmup; i++) {
                    int[] a = randomIntArray(n, new Random(rnd.nextLong()));
                    MergeSort.sort(a, new Counters());
                }
                System.out.println("Run MergeSort...");
                for (int t = 0; t < trials; t++) {
                    int[] a = randomIntArray(n, new Random(rnd.nextLong()));
                    Counters c = new Counters();
                    int[] input = Arrays.copyOf(a, a.length);
                    c.startTimer();
                    MergeSort.sort(input, c);
                    c.stopTimer();
                    writeCsvLine(w, "MergeSort", n, t, c);
                }

                // ---- QuickSort ----
                System.out.println("Warmup QuickSort...");
                for (int i = 0; i < warmup; i++) {
                    int[] a = randomIntArray(n, new Random(rnd.nextLong()));
                    QuickSort.sort(a, new Counters());
                }
                System.out.println("Run QuickSort...");
                for (int t = 0; t < trials; t++) {
                    int[] a = randomIntArray(n, new Random(rnd.nextLong()));
                    Counters c = new Counters();
                    int[] input = Arrays.copyOf(a, a.length);
                    c.startTimer();
                    QuickSort.sort(input, c);
                    c.stopTimer();
                    writeCsvLine(w, "QuickSort", n, t, c);
                }

                // ---- DeterministicSelect ----
                System.out.println("Warmup DeterministicSelect...");
                for (int i = 0; i < warmup; i++) {
                    int[] a = randomIntArray(n, new Random(rnd.nextLong()));
                    int k = Math.max(0, a.length / 2);
                    DeterministicSelect.select(Arrays.copyOf(a, a.length), k, new Counters());
                }
                System.out.println("Run DeterministicSelect...");
                for (int t = 0; t < trials; t++) {
                    int[] a = randomIntArray(n, new Random(rnd.nextLong()));
                    int k = Math.abs(new Random(rnd.nextLong()).nextInt()) % n;
                    Counters c = new Counters();
                    int[] input = Arrays.copyOf(a, a.length);
                    c.startTimer();
                    DeterministicSelect.select(input, k, c);
                    c.stopTimer();
                    writeCsvLine(w, "DeterministicSelect", n, t, c);
                }

                // ---- ClosestPair (2D) ----
                System.out.println("Warmup ClosestPair...");
                for (int i = 0; i < warmup; i++) {
                    ClosestPair.Point[] pts = randomPoints(n, new Random(rnd.nextLong()));
                    ClosestPair.closestPair(pts, new Counters());
                }
                System.out.println("Run ClosestPair...");
                for (int t = 0; t < trials; t++) {
                    ClosestPair.Point[] pts = randomPoints(n, new Random(rnd.nextLong()));
                    Counters c = new Counters();
                    c.startTimer();
                    ClosestPair.closestPair(pts, c);
                    c.stopTimer();
                    writeCsvLine(w, "ClosestPair", n, t, c);
                }

                w.flush();
            }

            System.out.println("Эксперименты завершены. Результаты в " + outFile);
        }
    }

    private static void writeCsvLine(BufferedWriter w, String algo, int n, int trial, Counters c) throws IOException {
        String line = String.format("%s,%d,%d,%d,%d,%d,%d,%d\n",
                algo, n, trial, c.getExecutionTime(),
                c.getComparisons(), c.getSwaps(), c.getAllocations(), c.getMaxDepth());
        w.write(line);
    }

    private static int[] randomIntArray(int n, Random rnd) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = rnd.nextInt(1_000_000); // 0..999999
        }
        return a;
    }

    private static ClosestPair.Point[] randomPoints(int n, Random rnd) {
        ClosestPair.Point[] pts = new ClosestPair.Point[n];
        for (int i = 0; i < n; i++) {
            double x = rnd.nextDouble() * 10000.0;
            double y = rnd.nextDouble() * 10000.0;
            pts[i] = new ClosestPair.Point(x, y);
        }
        return pts;
    }
}
