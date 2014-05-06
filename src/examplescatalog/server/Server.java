package examplescatalog.server;

import examplescatalog.catalog.ICatalog;
import examplescatalog.command.ICommand;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * Сервер слушает порт, принимает http-запросы и вызывает соответствующие команды.
 */
public class Server {
    private int port;
    private Executor executor;
    private ICatalog catalog;
    private Map<String, ICommand> commandMap;

    public Server(int port, Executor executor, ICatalog catalog, Map<String, ICommand> commandMap) {
        this.port = port;
        this.executor = executor;
        this.catalog = catalog;
        this.commandMap = commandMap;
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                executor.execute(new RequestProcessor(socket, commandMap, catalog));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}