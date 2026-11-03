import java.util.concurrent.*;

public class Deadlock {
    public static void main(String[] args) throws Exception {
        BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<>(1);
        BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<>(1);
        var executor = Executors.newVirtualThreadPerTaskExecutor();

        queue1.take();
        queue1.put(123);

        executor.execute(() -> {
            try {
                queue2.put(queue1.take() * 2);
            } catch (InterruptedException e) {
            }
        });

        executor.execute(() -> {
            try {
                queue1.put(queue2.take() + 1);
            } catch (InterruptedException e) {
            }
        });

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }
}
