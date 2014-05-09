package examplescatalog.catalog.filesystem;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Фильтрует идентификационный файл проекта.
 */
@Component
class PrIdFileFilter implements FilenameFilter {
    private String projectIdFilename;

    public PrIdFileFilter(String projectIdFilename) {
        this.projectIdFilename = projectIdFilename;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.equals(projectIdFilename);
    }
}
