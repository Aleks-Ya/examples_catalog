package examplescatalog.catalog.filesystem;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Фильтрует идентификационный файл проекта.
 */
class ProjectIdFileFilter implements FilenameFilter {
    private String projectIdFilename;

    public ProjectIdFileFilter(String projectIdFilename) {
        this.projectIdFilename = projectIdFilename;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.equals(projectIdFilename);
    }
}
