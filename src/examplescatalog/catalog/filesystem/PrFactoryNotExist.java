package examplescatalog.catalog.filesystem;

import examplescatalog.catalog.Catalog;
import examplescatalog.catalog.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Шаблон проекта со стандартными настройками.
 */
@Component
class PrFactoryNotExist {
    private static final Logger LOG = LoggerFactory.getLogger(PrFactoryNotExist.class);
    private String defaultCommand;
    private PrSaver prSaver;
    private Catalog catalog;
    private PrIdGenerator prIdGenerator;
    private PrIdList prIdList;

    @Autowired
    public PrFactoryNotExist(
            @Value("#{settings.defaultCommand}") String defaultCommand,
            PrSaver prSaver, Catalog catalog, PrIdGenerator prIdGenerator, PrIdList prIdList) {
        this.defaultCommand = defaultCommand;
        this.prSaver = prSaver;
        this.catalog = catalog;
        this.prIdGenerator = prIdGenerator;
        this.prIdList = prIdList;
    }

    @PostConstruct
    public void createProjects() throws IOException {
        for (File prDir : prIdList.getPrWithoutIdFile()) {
            String id = prIdGenerator.generateId();
            createProject(prDir, id);
        }
    }

    public void createProject(File folder, String prId) throws IOException {
        Project pr = new Project(prId, folder.getName(), folder, defaultCommand);
        LOG.info("Project created: {}", pr);
        prSaver.save(pr);
        catalog.addProject(pr);
    }
}
