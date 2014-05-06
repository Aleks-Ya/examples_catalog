package examplescatalog.server;

import examplescatalog.catalog.ICatalog;
import examplescatalog.catalog.Project;
import examplescatalog.command.ICommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Map;

/**
 * Обработчик запроса к серверу.
 */
class RequestProcessor implements Runnable {
    private Socket socket;
    private Map<String, ICommand> commandMap;
    private ICatalog catalog;

    RequestProcessor(Socket socket, Map<String, ICommand> commandMap, ICatalog catalog) {
        this.socket = socket;
        this.commandMap = commandMap;
        this.catalog = catalog;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String head = reader.readLine();
            reader.close();

            String[] strings = head.split(" ");
            String projectId = strings[1].substring(1);
            Project project = catalog.getProjectById(projectId);

            ICommand command = commandMap.get("explorerCommand");
            command.execute(project);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
