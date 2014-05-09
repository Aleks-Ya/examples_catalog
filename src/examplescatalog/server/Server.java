package examplescatalog.server;

import examplescatalog.catalog.Catalog;
import examplescatalog.command.ICommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * Сервер слушает порт, принимает http-запросы и вызывает соответствующие команды.
 */
@Component
public class Server {
    private static final Logger LOG = LoggerFactory.getLogger(Server.class);
    @Value("#{settings.port}")
    private int port;
    @Autowired
    private Executor executor;
    @Autowired
    private Catalog catalog;
    @Autowired
    private Map<String, ICommand> commandMap;

    @PostConstruct
    public void start() {
        LOG.info("Server started");
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                executor.execute(new RequestProcessor(socket, commandMap, catalog));
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
