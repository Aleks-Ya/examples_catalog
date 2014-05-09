package examplescatalog.catalog.filesystem;

import examplescatalog.catalog.Catalog;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Создает объекты Project на основе 2х списков папок:
 * 1. Папки с идентификационным файлом проекта
 * 2. Без идентификационного файла
 */
@Component
class PrFactoryExist {
    private Catalog catalog = new Catalog();
    private PrIdList prFolderList;
    private PrSaver prSaver;

    public PrFactoryExist(PrIdList prIdList, PrSaver prSaver) throws IOException {
        this.prFolderList = prIdList;
        this.prSaver = prSaver;
        init();
    }

    private void init() throws IOException {
        for (File prDir : prFolderList.getPrWithIdFile()) {
            catalog.addProject(prSaver.load(prDir));
        }
    }

    public Catalog getCatalog() {
        return catalog;
    }
}
