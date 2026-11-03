import java.util.concurrent.*;

public class Basics {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        Future<Integer> A = executor.submit(() -> 1 + 1);
        Future<Integer> B = executor.submit(() -> 2 + 2);
        Future<Integer> C = executor.submit(() -> A.get() + B.get());
        System.out.println(C.get());
    }
}

