package examplescatalog.catalog.filesystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Составляет список папок проектов.
 */
@Component
class PrFolderList {
    private static final Logger LOG = LoggerFactory.getLogger(PrFolderList.class);
    @Autowired
    @Qualifier("dff")
    private FileFilter dirFileFilter;
    @Autowired
    @Qualifier("pff")
    private FilenameFilter projectFileFilter;

    private List<File> projectFolders = new ArrayList<>();
    @Value("#{settings.examplesRoot}")
    private String rootCatalogDir;

    public PrFolderList() {
    }

    public PrFolderList(String rootCatalogDir, FileFilter dirFileFilter, FilenameFilter projectFileFilter) {
        this.rootCatalogDir = rootCatalogDir;
        this.dirFileFilter = dirFileFilter;
        this.projectFileFilter = projectFileFilter;
    }
@PostConstruct
    public void process() {
        LOG.info("Сканирую коневую папку проекта: {}", rootCatalogDir);
        processDir(new File(rootCatalogDir));
    }

    private void processDir(File dir) {
        File[] projectFiles = dir.listFiles(projectFileFilter);
        if (projectFiles.length > 0) {
            projectFolders.add(dir);
        }

        File[] subDirs = dir.listFiles(dirFileFilter);
        for (File subDir : subDirs) {
            processDir(subDir);
        }
    }

    public List<File> getProjectFolders() {
        return projectFolders;
    }
}