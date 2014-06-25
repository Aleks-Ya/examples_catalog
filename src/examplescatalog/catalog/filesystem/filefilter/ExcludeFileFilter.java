package examplescatalog.catalog.filesystem.filefilter;

import examplescatalog.settings.FileMask;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

/**
 * Пропускает файлы, подлежащие исключению из каталога.
 */
@Component
class ExcludeFileFilter implements FileFilter {
    @Value("#{settings.excludes}")
    private List<FileMask> masks;

    @Override
    public boolean accept(File file) {
        for (FileMask mask : masks) {
            if (mask.accept(file)) {
                return true;
            }
        }
        return false;
    }
}
