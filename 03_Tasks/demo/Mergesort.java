import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;

class Mergesort {

    public static int[] mergesort(int[] array, ExecutorService executor)
        throws InterruptedException, ExecutionException {

        if (array.length <= 1000) {
            Arrays.sort(array);
            return array;
        }

        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);

        Future<int[]> leftFuture = executor.submit(() -> mergesort(left, executor));
        Future<int[]> rightFuture = executor.submit(() -> mergesort(right, executor));

        int[] sortedLeft = leftFuture.get();
        int[] sortedRight = rightFuture.get();

        return merge(sortedLeft, sortedRight);
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }

        return result;
    }

    public static int[] randomArray() {
        int[] array = new int[10000000];
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(10000000);
        }
        return array;
    }

    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        int[] array = randomArray();

        long startTime = System.nanoTime();
        int[] sorted = array;
        Arrays.sort(array);
        long endTime = System.nanoTime();

        double duration = (endTime - startTime) / 1_000_000.0;
        System.out.println(Arrays.toString(Arrays.copyOfRange(sorted, 0, 10)));
        System.out.println("Time: " + duration + " ms");

    }
}

