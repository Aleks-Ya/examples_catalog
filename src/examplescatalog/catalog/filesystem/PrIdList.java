package examplescatalog.catalog.filesystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
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
    private List<File> prWithIdFile = new ArrayList<>();
    private List<File> prWithoutIdFile = new ArrayList<>();
    @Autowired
    private PrFolderList prFolderList;
    @Value("#{prIdFileFilter}")
    private FilenameFilter prIdFileFilter;

    void init() {
        for (File prFolder : prFolderList.getPrFolders()) {
            if (prFolder.listFiles(prIdFileFilter).length > 0) {
                prWithIdFile.add(prFolder);
            } else {
                prWithoutIdFile.add(prFolder);
            }
        }
        LOG.info("Found project folders WITH id file: {}", prWithIdFile.size());
        LOG.info("Found project folders WITHOUT id file: {}", prWithoutIdFile.size());
    }

    List<File> getPrWithIdFile() {
        return prWithIdFile;
    }

    List<File> getPrWithoutIdFile() {
        return prWithoutIdFile;
    }
}
