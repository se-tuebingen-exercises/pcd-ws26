import java.util.Optional;
import java.util.concurrent.*;

public class PoisonPill {
    public static void main(String[] args) throws Exception {
        BlockingQueue<Optional<Integer>> queue1 = new ArrayBlockingQueue<>(5);
        BlockingQueue<Optional<Integer>> queue2 = new ArrayBlockingQueue<>(5);
        var executor = Executors.newVirtualThreadPerTaskExecutor();
        executor.execute(() -> producer(queue1));
        executor.execute(() -> transformer(queue1, queue2));
        executor.execute(() -> consumer(queue2));
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);
    }

    static void producer(BlockingQueue<Optional<Integer>> queue) {
        try {
            for (int i = 0; i < 5; i++) {
                queue.put(Optional.of(i));
            }
            queue.put(Optional.empty());
        } catch (InterruptedException e) {
        }
    }

    static void transformer(BlockingQueue<Optional<Integer>> queue1, BlockingQueue<Optional<Integer>> queue2) {
        try {
            while (true) {
                Optional<Integer> value = queue1.take();
                if (value.isEmpty()) {
                    queue2.put(Optional.empty());
                    break;
                }
                queue2.put(Optional.of(value.get() + 1));
            }
        } catch (InterruptedException e) {
        }
    }

    static void consumer(BlockingQueue<Optional<Integer>> queue) {
        try {
            while (true) {
                Optional<Integer> value = queue.take();
                if (value.isEmpty()) break;
                System.out.println(value.get());
            }
        } catch (InterruptedException e) {
        }
    }
}
