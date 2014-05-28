package examplescatalog.cmd;

import examplescatalog.catalog.filesystem.filefilter.PrFileFilter;
import examplescatalog.settings.PrFileMask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * Выбирает проектный файл в папке проекта в соответствии с маской и приоритетом.
 */
@Component
public class PrFileResolver {

    @Autowired
    private PrFileFilter prFileFilter;

    private List<PrFileMask> sortedMaskList;

    @Autowired
    public PrFileResolver(@Value("#{settings.prFileMasks}") List<PrFileMask> prFileMasks) {
        Collections.sort(prFileMasks);
        Collections.reverse(prFileMasks);
        sortedMaskList = prFileMasks;
    }

    public File getAppropriatePrFile(File prDir) {
        File[] prFiles = prDir.listFiles(prFileFilter);
        for (PrFileMask mask : sortedMaskList) {
            for (File prFile : prFiles) {
                if (mask.accept(prFile)) {
                    return prFile;
                }
            }
        }
        throw new RuntimeException("Projects don't corresponds to masks");
    }
}