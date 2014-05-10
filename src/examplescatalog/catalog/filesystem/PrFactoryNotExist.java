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

/**
 * Шаблон проекта со стандартными настройками.
 */
@Component
class PrFactoryNotExist {
    private static final Logger LOG = LoggerFactory.getLogger(PrFactoryNotExist.class);
    @Value("#{settings.defaultCommand}")
    private String defaultCommand;
    @Autowired
    private PrSaver prSaver;
    @Autowired
    private Catalog catalog;
    @Autowired
    private PrIdGenerator prIdGenerator;
    @Autowired
    private PrIdList prIdList;

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
