package examplescatalog.catalog.filesystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Фильтрует идентификационный файл проекта.
 */
@Component
class PrIdFileFilter implements FilenameFilter {
    private String projectIdFilename;

    @Autowired
    public PrIdFileFilter(@Value("#{settings.projectIdFilename}") String projectIdFilename) {
        this.projectIdFilename = projectIdFilename;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.equals(projectIdFilename);
    }
}
