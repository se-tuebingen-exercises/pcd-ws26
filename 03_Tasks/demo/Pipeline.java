import java.util.concurrent.*;

public class Pipeline {
    public static void main(String[] args) throws Exception {
        BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<>(5);
        BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<>(5);
        var executor = Executors.newVirtualThreadPerTaskExecutor();
        executor.execute(() -> producer(queue1));
        executor.execute(() -> transformer(queue1, queue2));
        executor.execute(() -> consumer(queue2));
        executor.shutdown();
        executor.execute(() -> System.out.println("hallo"));
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    static void producer(BlockingQueue<Integer> queue) {
        try {
            for (int i = 0; i < 5; i++) {
                queue.put(i);
            }
        } catch (InterruptedException e) {
        }
    }

    static void transformer(BlockingQueue<Integer> queue1, BlockingQueue<Integer> queue2) {
        try {
            for (int i = 0; i < 5; i++) {
                queue2.put(queue1.take() + 1);
            }
        } catch (InterruptedException e) {
        }
    }

    static void consumer(BlockingQueue<Integer> queue) {
        try {
            for (int i = 0; i < 6; i++) {
                System.out.println(queue.take());
            }
        } catch (InterruptedException e) {
        }
    }
}

