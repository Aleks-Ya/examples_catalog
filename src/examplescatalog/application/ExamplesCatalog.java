package examplescatalog.application;

import examplescatalog.catalog.filesystem.PrFolderScanner;
import examplescatalog.cmd.CmdException;
import examplescatalog.server.CatalogServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Главный класс для запуска приложения.
 * todo Добавить в идентификационный файл свойство ignore=true для пропуска проекта в данной папке (или ignoreSubDirs), чтобы вложенные проекты не воспринимались каталогом
 * todo Добавить теги в идентификационный файл проекта
 * todo Сделать корневой папкой проекта по-умолчанию папку ~/examples_catalog
 */
public class ExamplesCatalog {
    private static final Logger LOG = LoggerFactory.getLogger(ExamplesCatalog.class);
    public static final String RUN_PROFILE = "run";

    public static void main(String[] args) throws CmdException {
        LOG.info("ExamplesCatalog started");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().addActiveProfile(RUN_PROFILE);
        context.scan("examplescatalog");
        context.refresh();

        context.getBean(PrFolderScanner.class).scan();
        context.getBean(CatalogServer.class).start();
    }
}