package examplescatalog.command;

import examplescatalog.catalog.Project;
import org.springframework.stereotype.Component;

/**
 * Команда "Завершить работу приложения".
 */
@Component
class ExitCommand implements ICommand {
    @Override
    public void execute(Project project) {
        System.exit(0);
    }
}
