package examplescatalog.catalog.filesystem;

import examplescatalog.catalog.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Составляет Catalog по папке с проектами.
 */
@Component
public class PrFolderScanner {
    @Autowired
    private Catalog catalog;

    @Autowired
    private PrIdGenerator prIdGenerator;

    public void scan() {
        catalog.setReady(false);
        catalog.clear();
        prIdGenerator.generate();
        catalog.setReady(true);
    }
}