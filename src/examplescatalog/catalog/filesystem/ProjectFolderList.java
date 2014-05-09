package examplescatalog.catalog.filesystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Составляет список папок проектов.
 */
@Component
public class ProjectFolderList {
    @Autowired
    @Qualifier("dff")
    private FileFilter dirFileFilter;
    @Autowired
    @Qualifier("pff")
    private FilenameFilter projectFileFilter;

    private List<File> projectFolders = new ArrayList<>();
    private File rootCatalogDir;

    public ProjectFolderList() {
    }

    public ProjectFolderList(File rootCatalogDir, FileFilter dirFileFilter, FilenameFilter projectFileFilter) {
        this.rootCatalogDir = rootCatalogDir;
        this.dirFileFilter = dirFileFilter;
        this.projectFileFilter = projectFileFilter;
    }

    public void process() {
        processDir(rootCatalogDir);
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