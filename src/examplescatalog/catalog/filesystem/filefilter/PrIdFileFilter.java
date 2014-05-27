package examplescatalog.catalog.filesystem.filefilter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Фильтрует идентификационный файл проекта.
 */
@Component
class PrIdFileFilter implements FilenameFilter {
    @Value("#{settings.prIdFilename}")
    private String prIdFilename;

    @Override
    public boolean accept(File dir, String name) {
        return name.equals(prIdFilename);
    }
}