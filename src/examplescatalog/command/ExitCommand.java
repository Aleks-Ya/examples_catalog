package examplescatalog.command;

import examplescatalog.catalog.Project;

/**
 * Команда "Завершить работу приложения".
 */
public class ExitCommand implements ICommand {
    @Override
    public void execute(Project project) {
        System.exit(0);
    }
}
