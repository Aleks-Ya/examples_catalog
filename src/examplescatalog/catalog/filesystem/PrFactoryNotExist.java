package examplescatalog.catalog.filesystem;

import examplescatalog.catalog.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Шаблон проекта со стандартными настройками.
 */
public class PrFactoryNotExist {
    private static final Logger LOG = LoggerFactory.getLogger(PrFactoryNotExist.class);
    private String defaultCommand;
    private PrSaver prSaver;

    public PrFactoryNotExist(String defaultCommand, PrSaver prSaver) {
        this.defaultCommand = defaultCommand;
        this.prSaver = prSaver;
    }

    public Project getInstance(File folder, String prId) throws IOException {
        Project pr = new Project(prId, folder.getName(), folder, defaultCommand);
        LOG.info("Создал проект {}", pr);
        prSaver.save(pr);
        return pr;
    }
}
