import java.awt.Graphics;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import javax.imageio.ImageIO;

public class ImagePipeline {

    public static void main(String[] args) throws Exception {

        Files.createDirectories(Path.of("out"));

        var executor = Executors.newVirtualThreadPerTaskExecutor();

        // TODO process images in a parallel pipeline

        executor.shutdown();
        executor.awaitTermination(2, TimeUnit.MINUTES);
    }

    static List<String> readUrls(String filename) throws IOException {
        return Files.readAllLines(Path.of(filename));
    }

    static BufferedImage downloadImage(String url) throws IOException {
        return ImageIO.read(URI.create(url).toURL());
    }

    static BufferedImage toGrayscale(BufferedImage image) {
        BufferedImage gray = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics graphics = gray.getGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
        return gray;
    }

    static void saveImage(BufferedImage image, String filename) throws IOException {
        ImageIO.write(image, "png", new File(filename));
    }
}
