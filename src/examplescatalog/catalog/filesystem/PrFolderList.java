package examplescatalog.catalog.filesystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Составляет список папок проектов.
 */
@Component
class PrFolderList {
    private static final Logger LOG = LoggerFactory.getLogger(PrFolderList.class);

    @Autowired
    @Qualifier("dirFileFilter")
    private FileFilter dirFileFilter;

    @Value("#{prFileFilter}")
    private FileFilter prFileFilter;
    private List<File> prFolders = new ArrayList<>();

    @Value("#{settings.examplesRoot}")
    private String rootCatalogDir;

    void process() {
        LOG.info("Scan root folder: {}", rootCatalogDir);
        Integer scannedDirCounter = 0;
        processDir(new File(rootCatalogDir), scannedDirCounter);
        LOG.info("Scanned folders: {}", scannedDirCounter);
    }

    private void processDir(File dir, int counter) {
        counter++;
        File[] prFiles = dir.listFiles(prFileFilter);
        if (prFiles.length > 0) {
            prFolders.add(dir);
        }

        File[] subDirs = dir.listFiles(dirFileFilter);
        for (File subDir : subDirs) {
            processDir(subDir, counter);
        }
    }

    List<File> getPrFolders() {
        return prFolders;
    }
}