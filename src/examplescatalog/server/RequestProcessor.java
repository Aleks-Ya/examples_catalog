package examplescatalog.server;

import examplescatalog.catalog.Catalog;
import examplescatalog.catalog.Project;
import examplescatalog.command.CommandException;
import examplescatalog.command.ICommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * Обработчик запроса к серверу.
 */
@Component
@Scope("prototype")
class RequestProcessor implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(RequestProcessor.class);
    private String target;
    private HttpServletRequest request;

    @Autowired
    private Catalog catalog;

    @Autowired
    private CommandResolver commandResolver;

    @Autowired
    private PrResolver prResolver;

    RequestProcessor(String target, HttpServletRequest request) {
        this.target = target;
        this.request = request;
    }

    @Override
    public void run() {
        try {
            while (!catalog.isReady()) {
                LOG.warn("Wait catalog ready");
                TimeUnit.SECONDS.sleep(1);
            }

            ICommand command = commandResolver.getCommand(target, request);
            Project project = prResolver.getProject(target, request);
            command.execute(project);
        } catch (CommandException | InterruptedException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}