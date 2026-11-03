import java.net.*;

public class Server2 {
    public static void main(String[] args) throws Exception {
        var server = new ServerSocket(8002);
        while (true) {
            var socket = server.accept();
            socket.getOutputStream().write("7\n".getBytes());
            socket.close();
        }
    }
}
