package examplescatalog.catalog.filesystem.filefilter;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileFilter;

/**
 * Файловый фильтр, пропускающий только директории.
 */
@Component
class DirFileFilter implements FileFilter {
    @Override
    public boolean accept(File pathname) {
        return pathname.isDirectory();
    }
}
