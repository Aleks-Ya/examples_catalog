package examplescatalog.server;

import examplescatalog.catalog.Catalog;
import examplescatalog.catalog.Project;
import examplescatalog.command.ICommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Обработчик запроса к серверу.
 */
class RequestProcessor implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(RequestProcessor.class);
    private HttpServletRequest request;
    private Map<String, ICommand> commandMap;
    private Catalog catalog;
    private String target;

    RequestProcessor(String target, HttpServletRequest request, Map<String, ICommand> commandMap, Catalog catalog) {
        this.target = target;
        this.request = request;
        this.commandMap = commandMap;
        this.catalog = catalog;
    }

    @Override
    public void run() {
        try {
            while (!catalog.isReady()) {
                LOG.warn("Wait catalog ready");
                TimeUnit.SECONDS.sleep(1);
            }
            String prId = target.replace("/","");
            LOG.info("Received project id: {}", prId);
            Project project = catalog.getPrById(prId);
            if (project == null) {
                throw new ServerException(String.format("Project not found by id: %s", prId));
            }

            //todo применить команду RescanCommand
            ICommand command = commandMap.get("explorerCommand");
            command.execute(project);
        } catch (ServerException | InterruptedException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}