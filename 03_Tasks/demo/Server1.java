import java.net.*;

public class Server1 {
    public static void main(String[] args) throws Exception {
        var server = new ServerSocket(8001);
        while (true) {
            var socket = server.accept();
            socket.getOutputStream().write("5\n".getBytes());
            socket.close();
        }
    }
}
