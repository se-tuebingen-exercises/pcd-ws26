import java.util.concurrent.*;

public class Graph {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        int[] nodes = { 3 };
        int[][] graph = { { 0, 1 }, {}, { 0 }, { 0, 1, 2, 3 } };

        int result = countReachableFrom2(nodes, graph, executor);

        System.out.println("Reachable: " + result);
    }

    public static int countReachableFrom(int[] nodes, int[][] graph, ExecutorService executor) throws Exception {
        @SuppressWarnings("unchecked")
        Future<Integer>[] futures = new Future[nodes.length];

        for (int i = 0; i < nodes.length; i++) {
            int node = nodes[i];
            futures[i] = executor.submit(() -> graph[node].length);
        }

        int result = 0;
        for (int i = 0; i < futures.length; i++) {
            result += futures[i].get();
        }
        return result;
    }

    public static int countReachableFrom2(int[] nodes, int[][] graph, ExecutorService executor) throws Exception {
        @SuppressWarnings("unchecked")
        Future<Integer>[] futures = new Future[nodes.length];

        for (int i = 0; i < nodes.length; i++) {
            int node = nodes[i];
            futures[i] = executor.submit(() -> countReachableFrom(graph[node], graph, executor));
        }

        int result = 0;
        for (int i = 0; i < futures.length; i++) {
            result += futures[i].get();
        }
        return result;
    }

}
