package examplescatalog.command;

import examplescatalog.catalog.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Команда "Завершить работу приложения".
 */
@Component
class ExitCommand implements ICommand {
    private static final Logger LOG = LoggerFactory.getLogger(ExitCommand.class);

    @Override
    public void execute(Project project) {
        LOG.info("Exit command");
        System.exit(0);
    }
}