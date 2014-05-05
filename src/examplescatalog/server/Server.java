package examplescatalog.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Сервер слушает порт, принимает http-запросы и вызывает соответствующие команды.
 */
public class Server {
    private int port;
    private Executor executor = Executors.newFixedThreadPool(10);

    public Server(int port) {
        this.port = port;
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                executor.execute(new RequestProcessor(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
