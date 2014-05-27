package examplescatalog.cmd;

import examplescatalog.catalog.Project;

/**
 * Команда, выполняемая в ответ на запрос серверу.
 */
public interface ICmd {

    /**
     * Выполнить команду.
     */
    void execute(Project project) throws CmdException;
}