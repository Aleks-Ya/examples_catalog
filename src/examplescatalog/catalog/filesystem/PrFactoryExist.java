package examplescatalog.catalog.filesystem;

import examplescatalog.catalog.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private Catalog catalog;
    @Autowired
    private PrIdList prFolderList;
    @Autowired
    private PrSaver prSaver;

    void init() throws IOException {
        for (File prDir : prFolderList.getPrWithIdFile()) {
            catalog.addProject(prSaver.load(prDir));
        }
    }
}