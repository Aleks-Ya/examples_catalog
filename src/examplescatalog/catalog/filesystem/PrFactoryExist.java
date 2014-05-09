package examplescatalog.catalog.filesystem;

import examplescatalog.catalog.ICatalog;

import java.io.File;
import java.io.IOException;

/**
 * Создает объекты Project на основе 2х списков папок:
 * 1. Папки с идентификационным файлом проекта
 * 2. Без идентификационного файла
 */
public class PrFactoryExist {
    private ICatalog catalog = new FileSystemCatalog();
    private PrIdList prFolderList;
    private PrSaver prSaver;

    public PrFactoryExist(PrIdList prIdList, PrSaver prSaver) throws IOException {
        this.prFolderList = prIdList;
        init();
    }

    private void init() throws IOException {
        for (File prDir : prFolderList.getPrWithIdFile()) {
            catalog.addProject(prSaver.load(prDir));
        }
    }

    public ICatalog getCatalog() {
        return catalog;
    }
}
