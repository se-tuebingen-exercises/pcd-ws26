import java.util.*;
import java.util.concurrent.*;

class SocialNetwork {
    static Set<Integer> findFollowers(int[][] graph, int person, int depth, ExecutorService executor) throws Exception {
        if (depth == 0) {
            return new HashSet<>();
        }
        Set<Integer> result = new HashSet<>();
        for (int follower : graph[person]) {
            result.add(follower);
        }

        List<Future<Set<Integer>>> futures = new ArrayList<>();

        for (int follower : graph[person]) {
            futures.add(executor.submit(() -> findFollowers(graph, follower, depth - 1, executor)));
        }

        for (Future<Set<Integer>> future : futures) {
            result.addAll(future.get());
        }

        return result;
    }

    public static int[][] randomGraph() {
        Random random = new Random(42);
        int[][] graph = new int[1000000][];
        for (int i = 0; i < graph.length; i++) {
            int numFollowers = random.nextInt(100);
            graph[i] = new int[numFollowers];
            for (int j = 0; j < numFollowers; j++) {
                graph[i][j] = random.nextInt(graph.length);
            }
        }
        return graph;
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        int[][] graph = randomGraph();

        long startTime = System.nanoTime();
        Set<Integer> result = findFollowers(graph, 0, 4, executor);
        long endTime = System.nanoTime();

        double duration = (endTime - startTime) / 1_000_000.0;
        System.out.println(result.size());
        System.out.println("Time: " + duration + " ms");
    }
}
