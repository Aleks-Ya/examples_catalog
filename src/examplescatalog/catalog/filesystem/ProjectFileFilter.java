package examplescatalog.catalog.filesystem;

import examplescatalog.settings.ProjectFileMask;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Файловый фильт проектных файлов.
 */
@Component("pff")
class ProjectFileFilter implements FilenameFilter {
    private List<Pattern> masks = new ArrayList<>();
    @Value("#{settings.projectFileMasks}")
    private List<ProjectFileMask> projectFileMasks;

    @PostConstruct
    private void init() {
        for (ProjectFileMask mask : projectFileMasks) {
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
