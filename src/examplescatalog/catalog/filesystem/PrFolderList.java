package examplescatalog.catalog.filesystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Составляет список папок проектов.
 */
@Component
class PrFolderList {
    private static final Logger LOG = LoggerFactory.getLogger(PrFolderList.class);
    private DirFileFilter dirFileFilter;
    private PrFileFilter projectFileFilter;
    private List<File> projectFolders = new ArrayList<>();
    private String rootCatalogDir;

    @Autowired
    public PrFolderList(
            @Value("#{settings.examplesRoot}") String rootCatalogDir,
            DirFileFilter dirFileFilter, PrFileFilter projectFileFilter) {
        this.rootCatalogDir = rootCatalogDir;
        this.dirFileFilter = dirFileFilter;
        this.projectFileFilter = projectFileFilter;
    }

    @PostConstruct
    public void process() {
        LOG.info("Scan root folder: {}", rootCatalogDir);
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