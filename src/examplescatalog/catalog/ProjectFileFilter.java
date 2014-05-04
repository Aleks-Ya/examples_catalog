package examplescatalog.catalog;

import examplescatalog.settings.ProjectFileMask;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

/**
 * Файловый фильт проектных файлов.
 */
public class ProjectFileFilter implements FilenameFilter {
    private List<ProjectFileMask> masks;

    public ProjectFileFilter(List<ProjectFileMask> masks) {
        this.masks = masks;
    }

    @Override
    public boolean accept(File dir, String name) {
        for (ProjectFileMask mask : masks) {
            if (name.matches(mask.getMask())) {
                return true;
            }
        }
        return false;
    }
}
