package examplescatalog.command;

import examplescatalog.catalog.Project;

/**
 * Команда, выполняемая в ответ на запрос серверу.
 */
public interface ICommand {

    /**
     * Выполнить команду.
     */
    void execute(Project project) throws CommandException;
}