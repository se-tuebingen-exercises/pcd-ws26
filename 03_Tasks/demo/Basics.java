import java.util.concurrent.*;

public class Basics {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        Future<Integer> A;
        Future<Integer> B;
        A = executor.submit(() -> 1 + B.get());
        B = executor.submit(() -> 2 + A.get());
        Future<Integer> C = executor.submit(() -> A.get() + B.get());

        System.out.println(C.get());
    }
}

