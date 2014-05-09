package examplescatalog.catalog.dircatalog;

import examplescatalog.settings.PrFileMask;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Файловый фильт проектных файлов.
 */
class ProjectFileFilter implements FilenameFilter {
    private List<Pattern> masks = new ArrayList<>();

    public ProjectFileFilter(List<PrFileMask> masks) {
        for (PrFileMask mask : masks) {
            this.masks.add(Pattern.compile(mask.getMask()));
        }
    }

    @Override
    public boolean accept(File dir, String name) {
        for (Pattern mask : masks) {
            if (mask.matcher(name).matches()) {
                return true;
            }
        }
        return false;
    }
}
