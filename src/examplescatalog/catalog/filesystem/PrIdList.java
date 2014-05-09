package examplescatalog.catalog.filesystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Составляет 2 списка файлов проект:
 * 1. С идентификационным файлом проекта;
 * 2. Без идентификационного файла проекта.
 */
@Component
class PrIdList {
    private static final Logger LOG = LoggerFactory.getLogger(PrIdList.class);
    private List<File> projectWithIdFile = new ArrayList<>();
    private List<File> projectWithoutIdFile = new ArrayList<>();
    private PrFolderList prFolderList;
    private PrIdFileFilter prIdFileFilter;

    @Autowired
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
        LOG.info("Found project folders WITH id file: {}", projectWithIdFile.size());
        LOG.info("Found project folders WITHOUT id file: {}", projectWithoutIdFile.size());
    }

    public List<File> getPrWithIdFile() {
        return projectWithIdFile;
    }

    public List<File> getPrWithoutIdFile() {
        return projectWithoutIdFile;
    }
}
