package examplescatalog.catalog.filesystem;

import examplescatalog.catalog.Catalog;
import examplescatalog.cmd.CmdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Составляет Catalog по папке с проектами.
 */
@Component
public class PrFolderScanner {
    @Autowired
    private Catalog catalog;

    @Autowired
    private PrIdGenerator prIdGenerator;

    @Autowired
    private PrIdList prIdList;

    @Autowired
    private PrFolderList prFolderList;

    @Autowired
    private PrFactoryExist prFactoryExist;

    @Autowired
    private PrFactoryNotExist prFactoryNotExist;

    public void scan() throws CmdException {
        try {
            catalog.setReady(false);
            catalog.clear();
            prFolderList.process();
            prIdList.init();
            prFactoryExist.init();
            prFactoryNotExist.createPrs();
            prIdGenerator.generate();
            catalog.setReady(true);
        } catch (IOException e) {
            throw new CmdException(e);
        }
    }
}