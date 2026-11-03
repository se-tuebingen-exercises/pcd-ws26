import java.util.concurrent.*;
import java.util.*;

public class Batching {
    public static void main(String[] args) throws Exception {
        BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<>(5);
        BlockingQueue<List<Integer>> queue2 = new ArrayBlockingQueue<>(5);
        var executor = Executors.newVirtualThreadPerTaskExecutor();

        executor.execute(() -> producer(queue1));
        executor.execute(() -> transformer(queue1, queue2));
        executor.execute(() -> consumer(queue2));

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    static void producer(BlockingQueue<Integer> queue1) {
        try {
            for (int i = 0; i < 50; i++) {
                queue1.put(i);
            }
        } catch (InterruptedException e) {}
    }

    static void transformer(BlockingQueue<Integer> queue1, BlockingQueue<List<Integer>> queue2) {
        try {
            for (int batch = 0; batch < 5; batch++) {
                List<Integer> items = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    items.add(queue1.take());
                }
                List<Integer> results = items.parallelStream().map(x -> x + 1).toList();
                queue2.put(results);
            }
        } catch (InterruptedException e) {}
    }

    static void consumer(BlockingQueue<List<Integer>> queue3) {
        try {
            for (int batch = 0; batch < 5; batch++) {
                System.out.println(queue3.take());
            }
        } catch (InterruptedException e) {}
    }
}

