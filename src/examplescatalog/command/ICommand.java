package examplescatalog.command;

import examplescatalog.catalog.Project;

/**
 * Команда, выполняемая в ответ на запрос серверу.
 * todo Добавить команду rescan
 */
public interface ICommand {
    void execute(Project project);
}
