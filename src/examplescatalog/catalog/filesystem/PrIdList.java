package examplescatalog.catalog.filesystem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Составляет 2 списка файлов проект:
 * 1. С идентификационным файлом проекта;
 * 2. Без идентификационного файла проекта.
 */
class PrIdList {
    private List<File> projectWithIdFile = new ArrayList<>();
    private List<File> projectWithoutIdFile = new ArrayList<>();
    private PrFolderList prFolderList;
    private PrIdFileFilter prIdFileFilter;

    public PrIdList(PrFolderList prFolderList, PrIdFileFilter prIdFileFilter) {
        this.prFolderList = prFolderList;
        this.prIdFileFilter = prIdFileFilter;
        init();
    }

    private void init() {
        for (File projectFolder : prFolderList.getProjectFolders()) {
            if (projectFolder.listFiles(prIdFileFilter).length > 0) {
                projectWithIdFile.add(projectFolder);
            } else {
                projectWithoutIdFile.add(projectFolder);
            }
        }
    }

    public List<File> getPrWithIdFile() {
        return projectWithIdFile;
    }

    public List<File> getPrWithoutIdFile() {
        return projectWithoutIdFile;
    }
}
