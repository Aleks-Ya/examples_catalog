package examplescatalog.catalog.filesystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
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
    @Autowired
    private PrFolderList prFolderList;
    @Value("#{prIdFileFilter}")
    private FilenameFilter prIdFileFilter;

    @PostConstruct
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
