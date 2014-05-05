package examplescatalog.catalog.dircatalog;

import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Папка на диске.
 */
public class Folder {
    private File dir;
    private FilenameFilter projectFileFilter;

    public Folder(File dir, FilenameFilter projectFileFilter) {
        this.dir = dir;
        this.projectFileFilter = projectFileFilter;
    }

    /**
     * Данная папка содержит проект примера?
     */
    public boolean isProjectFolder() {
        return ArrayUtils.isEmpty(dir.listFiles(projectFileFilter));
    }

    public List<ProjectIdFile> getProjectIdFiles() {
        List<ProjectIdFile> result = new ArrayList<>();
        for (File projectFile : dir.listFiles(projectFileFilter)) {
            result.add(new ProjectIdFile(projectFile));
        }
        return result;
    }
}