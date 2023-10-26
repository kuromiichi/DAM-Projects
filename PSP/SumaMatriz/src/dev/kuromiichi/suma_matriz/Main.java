package dev.kuromiichi.suma_matriz;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int size = 1000;
        int[][] matrix = new int[size][size];

        int range = 100; // Multiple of 10
        fillMatrix(matrix, range);

        // Execution times (5 function calls)
        int calls = 10;
        long[] times = new long[calls];

        // No threads
        for (int i = 0; i < calls; i++) {
            long start = System.currentTimeMillis();
            long sum = sumMatrix(matrix);
            long end = System.currentTimeMillis();
            times[i] = end - start;
            System.out.println("Single thread (regular): " + sum + " in " + times[i] + "ms");
        }
        System.out.println("Average (no threads): " + Arrays.stream(times).average().orElse(-1) + "ms");
        System.out.println();

        // Single thread
        for (int i = 0; i < calls; i++) {
            long start = System.currentTimeMillis();
            long sum = sumMatrixThreaded(matrix, 1);
            long end = System.currentTimeMillis();
            times[i] = end - start;
            System.out.println("Single thread (threaded): " + sum + " in " + times[i] + "ms");
        }
        System.out.println("Average (1 thread): " + Arrays.stream(times).average().orElse(-1) + "ms");
        System.out.println();

        // 2 threads
        for (int i = 0; i < calls; i++) {
            long start = System.currentTimeMillis();
            long sum = sumMatrixThreaded(matrix, 2);
            long end = System.currentTimeMillis();
            times[i] = end - start;
            System.out.println("2 threads: " + sum + " in " + times[i] + "ms");
        }
        System.out.println("Average (2 threads): " + Arrays.stream(times).average().orElse(-1) + "ms");
        System.out.println();

        // 4 threads
        for (int i = 0; i < calls; i++) {
            long start = System.currentTimeMillis();
            long sum = sumMatrixThreaded(matrix, 4);
            long end = System.currentTimeMillis();
            times[i] = end - start;
            System.out.println("4 threads: " + sum + " in " + times[i] + "ms");
        }
        System.out.println("Average (4 threads): " + Arrays.stream(times).average().orElse(-1) + "ms");
        System.out.println();

        // 8 threads
        for (int i = 0; i < calls; i++) {
            long start = System.currentTimeMillis();
            long sum = sumMatrixThreaded(matrix, 8);
            long end = System.currentTimeMillis();
            times[i] = end - start;
            System.out.println("8 threads: " + sum + " in " + times[i] + "ms");
        }
        System.out.println("Average (8 threads): " + Arrays.stream(times).average().orElse(-1) + "ms");
        System.out.println();

        // 16 threads
        for (int i = 0; i < calls; i++) {
            long start = System.currentTimeMillis();
            long sum = sumMatrixThreaded(matrix, 16);
            long end = System.currentTimeMillis();
            times[i] = end - start;
            System.out.println("16 threads: " + sum + " in " + times[i] + "ms");
        }
        System.out.println("Average (16 threads): " + Arrays.stream(times).average().orElse(-1) + "ms");
        System.out.println();
    }

    private static void fillMatrix(int[][] matrix, int range) {
        Random random = new Random(System.currentTimeMillis());
        for (int[] row : matrix) {
            for (int i = 0; i < row.length; i++) {
                row[i] = random.nextInt(range) + 1;
            }
        }
    }

    private static long sumMatrix(int[][] matrix) {
        long sum = 0;
        for (int[] row : matrix) {
            for (int num : row) {
                sum += num;
            }
        }
        return sum;
    }

    private static long sumMatrixThreaded(int[][] matrix, int numThreads) throws InterruptedException {
        int rowsPerThread = matrix.length / numThreads;
        int remainingRows = matrix.length % numThreads;
        Thread[] threads = new Thread[numThreads];
        MatrixAdder[] adders = new MatrixAdder[numThreads];
        long sum = 0;

        for (int i = 0; i < numThreads; i++) {
            int[][] subMatrix = (i == 0)
                    ? Arrays.copyOfRange(matrix, 0, rowsPerThread + remainingRows)
                    : Arrays.copyOfRange(matrix, rowsPerThread * i + remainingRows, rowsPerThread * (i + 1) + remainingRows);
            adders[i] = new MatrixAdder(subMatrix);
            threads[i] = new Thread(adders[i]);
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        for (MatrixAdder adder : adders) {
            sum += adder.getSum();
        }

        return sum;
    }

}
