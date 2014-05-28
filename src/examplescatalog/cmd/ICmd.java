package examplescatalog.cmd;

import examplescatalog.catalog.Pr;

/**
 * Команда, выполняемая в ответ на запрос серверу.
 */
public interface ICmd {

    /**
     * Выполнить команду.
     */
    void execute(Pr pr) throws CmdException;
}