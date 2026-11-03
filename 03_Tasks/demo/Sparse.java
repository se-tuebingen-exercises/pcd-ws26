import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Sparse {

    static class CSR {
        int[] rows;
        int[] columns;
        double[] values;
    }

    public static void main(String[] args) {
        CSR matrix = generateMatrix(100000);
        double[] vector = generateVector(100000);

        long start = System.nanoTime();
        double[] result = multiply(matrix, vector);
        long time = System.nanoTime() - start;

        System.out.println(time / 1e6 + " ms");
        // System.out.println(java.util.Arrays.toString(result));
    }

    public static double[] multiply(CSR matrix, double[] vector) {
        double[] result = new double[matrix.rows.length - 1];
        IntStream.range(0, matrix.rows.length - 1).parallel().forEach(i -> {
            double sum = 0.0;
            for (int j = matrix.rows[i]; j < matrix.rows[i + 1]; j++) {
                sum += matrix.values[j] * vector[matrix.columns[j]];
            }
            result[i] = sum;
        });
        return result;
    }

    static CSR generateMatrix(int n) {
        Random rand = new Random();
        CSR matrix = new CSR();
        matrix.rows = new int[n + 1];

        List<Double> valuesList = new ArrayList<>();
        List<Integer> columnsList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            matrix.rows[i] = valuesList.size();
            for (int j = 0; j < n; j++) {
                if (rand.nextDouble() < 0.1) {
                    valuesList.add(rand.nextDouble());
                    columnsList.add(j);
                }
            }
        }
        matrix.rows[n] = valuesList.size();

        matrix.values = new double[valuesList.size()];
        matrix.columns = new int[columnsList.size()];
        for (int i = 0; i < valuesList.size(); i++) {
            matrix.values[i] = valuesList.get(i);
            matrix.columns[i] = columnsList.get(i);
        }

        return matrix;
    }

    static double[] generateVector(int n) {
        Random rand = new Random();
        double[] vector = new double[n];
        for (int i = 0; i < n; i++) {
            vector[i] = rand.nextDouble();
        }
        return vector;
    }
}

