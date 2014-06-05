package examplescatalog.cmd;

import examplescatalog.catalog.Pr;

/**
 * Команда, выполняемая в ответ на запрос серверу.
 * todo Добавить командам алиасы
 * todo Реализовать команду запуска NetBeans
 */
public interface ICmd {

    /**
     * Выполнить команду.
     */
    void execute(Pr pr) throws CmdException;
}