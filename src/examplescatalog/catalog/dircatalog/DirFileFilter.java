package examplescatalog.catalog.dircatalog;

import java.io.File;
import java.io.FileFilter;

/**
 * Файловый фильтр, пропускающий только директории.
 */
class DirFileFilter implements FileFilter {
    @Override
    public boolean accept(File pathname) {
        return pathname.isDirectory();
    }
}
