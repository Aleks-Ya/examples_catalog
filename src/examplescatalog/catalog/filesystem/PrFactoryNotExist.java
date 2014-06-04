package examplescatalog.catalog.filesystem;

import examplescatalog.catalog.Catalog;
import examplescatalog.catalog.Pr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Шаблон проекта со стандартными настройками.
 */
@Component
class PrFactoryNotExist {
    private static final Logger LOG = LoggerFactory.getLogger(PrFactoryNotExist.class);
    @Autowired
    private PrSaver prSaver;
    @Autowired
    private Catalog catalog;
    @Autowired
    private PrIdGenerator prIdGenerator;
    @Autowired
    private PrIdList prIdList;

    void createPrs() throws IOException {
        for (File prDir : prIdList.getPrWithoutIdFile()) {
            String id = prIdGenerator.generateId();
            createPr(prDir, id);
        }
    }

    private void createPr(File folder, String prId) throws IOException {
        Pr pr = new Pr(prId, folder.getName(), folder, null);
        LOG.info("Project created: {}", pr);
        prSaver.save(pr);
        catalog.addPr(pr);
    }
}