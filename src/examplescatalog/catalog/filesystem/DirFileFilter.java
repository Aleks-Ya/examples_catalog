package examplescatalog.catalog.filesystem;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileFilter;

/**
 * Файловый фильтр, пропускающий только директории.
 */
@Component("dff")
class DirFileFilter implements FileFilter {
    @Override
    public boolean accept(File pathname) {
        return pathname.isDirectory();
    }
}
