package examplescatalog.command;

import examplescatalog.catalog.Project;

/**
 * Команда, выполняемая в ответ на запрос серверу.
 */
public interface ICommand {
    void execute(Project project);
}
