package examplescatalog.catalog.filesystem;

import examplescatalog.settings.PrFileMask;
import org.springframework.beans.factory.annotation.Autowired;
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
@Component
class PrFileFilter implements FilenameFilter {
    private List<Pattern> masks = new ArrayList<>();
    private List<PrFileMask> prFileMasks;

    @Autowired
    PrFileFilter(@Value("#{settings.projectFileMasks}") List<PrFileMask> prFileMasks) {
        this.prFileMasks = prFileMasks;
        init();
    }

    private void init() {
        for (PrFileMask mask : prFileMasks) {
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
