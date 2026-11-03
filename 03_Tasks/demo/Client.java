import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Client {
    public static void main(String[] args) throws Exception {

        var executor = Executors.newVirtualThreadPerTaskExecutor();

        Future<Integer> A = executor.submit(() -> fetch(8001));
        Future<Integer> B = executor.submit(() -> fetch(8002));

        System.out.println(A.get() + B.get());

        executor.shutdown();
    }

    static int fetch(int port) throws Exception {
        var socket = new Socket("localhost", port);
        var input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        var result = Integer.parseInt(input.readLine());
        socket.close();
        return result;
    }
}
