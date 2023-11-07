package dev.kuromiichi.suma_matriz;

public class MatrixAdder implements Runnable {
   private int[][] matrix;
   private long sum;

    public MatrixAdder(int[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public void run() {
        for (int[] row : matrix) {
            for (int num : row) {
                sum += num;
            }
        }
    }

    public long getSum() {
        return sum;
    }

}
