package examplescatalog.catalog.filesystem;

import examplescatalog.catalog.ICatalog;
import examplescatalog.catalog.Project;

import java.io.File;
import java.io.IOException;

/**
 * Создает объекты Project на основе 2х списков папок:
 * 1. Папки с идентификационным файлом проекта
 * 2. Без идентификационного файла
 */
public class ExistProjectFactory {
    private ICatalog catalog = new FileSystemCatalog();
    private ProjectIdList prFolderList;
    private PrSaver prSaver;

    public ExistProjectFactory(ProjectIdList prIdList, PrSaver prSaver) throws IOException {
        this.prFolderList = prIdList;
        init();
    }

    private void init() throws IOException {
        for (File prDir : prFolderList.getProjectWithIdFile()) {
            catalog.addProject(prSaver.load(prDir));
        }
    }

    public ICatalog getCatalog() {
        return catalog;
    }
}
