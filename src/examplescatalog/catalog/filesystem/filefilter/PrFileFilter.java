package examplescatalog.catalog.filesystem.filefilter;

import examplescatalog.settings.PrFileMask;
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
    @Value("#{settings.prFileMasks}")
    private List<PrFileMask> prFileMasks;

    @PostConstruct
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