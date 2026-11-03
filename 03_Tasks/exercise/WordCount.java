import java.net.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

public class WordCount {

    public static void main(String[] args) throws Exception {

        List<String> urls = Files.readAllLines(Path.of("books.csv"));

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        // TODO process books in parallel

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }

    static HashMap<String, Integer> processBook(String urlString) throws Exception {
        URL url = URI.create(urlString).toURL();
        String text = new String(url.openStream().readAllBytes());
        String[] words = text.toLowerCase().split("\\W+");
        HashMap<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            counts.merge(word, 1, Integer::sum);
        }
        return counts;
    }
}
