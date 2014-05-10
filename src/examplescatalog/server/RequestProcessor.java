package examplescatalog.server;

import examplescatalog.catalog.Catalog;
import examplescatalog.catalog.Project;
import examplescatalog.command.ICommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Map;

/**
 * Обработчик запроса к серверу.
 */
class RequestProcessor implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(RequestProcessor.class);
    private Socket socket;
    private Map<String, ICommand> commandMap;
    private Catalog catalog;

    RequestProcessor(Socket socket, Map<String, ICommand> commandMap, Catalog catalog) {
        this.socket = socket;
        this.commandMap = commandMap;
        this.catalog = catalog;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //todo Вывести на html-страничку содержимое идентификационного файла проекта и файла description.txt
            String head = reader.readLine();
            reader.close();

            String[] strings = head.split(" ");
            String prId = strings[1].substring(1);
            LOG.info("Received project id: {}", prId);
            Project project = catalog.getPrById(prId);
            if (project == null) {
                throw new ServerException(String.format("Project not found by id: %s", prId));
            }

            ICommand command = commandMap.get("explorerCommand");
            command.execute(project);
        } catch (IOException | ServerException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
