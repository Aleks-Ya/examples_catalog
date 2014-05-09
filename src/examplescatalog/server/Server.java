package examplescatalog.server;

import examplescatalog.catalog.ICatalog;
import examplescatalog.command.ICommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private ICatalog catalog;
    @Autowired
    private Map<String, ICommand> commandMap;

//    public Server(int port, Executor executor, ICatalog catalog, Map<String, ICommand> commandMap) {
//        this.port = port;
//        this.executor = executor;
//        this.catalog = catalog;
//        this.commandMap = commandMap;
//    }

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
