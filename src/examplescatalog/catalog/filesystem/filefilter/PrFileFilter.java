package examplescatalog.catalog.filesystem.filefilter;

import examplescatalog.settings.PrFileMask;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

/**
 * Файловый фильт проектных файлов.
 */
@Component
public class PrFileFilter implements FileFilter {
    @Value("#{settings.prFileMasks}")
    private List<PrFileMask> prFileMasks;

    @Override
    public boolean accept(File dir) {
        for (PrFileMask mask : prFileMasks) {
            if (mask.accept(dir)) {
                return true;
            }
        }
        return false;
    }
}