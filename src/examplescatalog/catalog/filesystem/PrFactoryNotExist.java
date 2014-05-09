package examplescatalog.catalog.filesystem;

import examplescatalog.catalog.ICatalog;
import examplescatalog.catalog.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Шаблон проекта со стандартными настройками.
 */
class PrFactoryNotExist {
    private static final Logger LOG = LoggerFactory.getLogger(PrFactoryNotExist.class);
    private String defaultCommand;
    private PrSaver prSaver;
    private ICatalog catalog;
    private PrIdGenerator prIdGenerator;

    public PrFactoryNotExist(String defaultCommand, PrSaver prSaver, ICatalog catalog, PrIdGenerator prIdGenerator) {
        this.defaultCommand = defaultCommand;
        this.prSaver = prSaver;
        this.catalog = catalog;
        this.prIdGenerator = prIdGenerator;
    }

    public void createProjects(List<File> projects) throws IOException {
        for (File prDir : projects) {
            String id = prIdGenerator.generateId();
            createProject(prDir, id);
        }
    }

    public void createProject(File folder, String prId) throws IOException {
        Project pr = new Project(prId, folder.getName(), folder, defaultCommand);
        LOG.info("Создал проект {}", pr);
        prSaver.save(pr);
        catalog.addProject(pr);
    }
}
