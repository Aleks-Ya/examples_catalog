package examplescatalog.catalog.filesystem;

import examplescatalog.catalog.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * Читает и записывает идентификационный файл проекта.
 */
@Component
class PrSaver {
    private static final Logger LOG = LoggerFactory.getLogger(PrSaver.class);
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DEFAULT_COMMAND = "default_command";
    @Value("#{settings.projectIdFilename}")
    private String prIdFilename;

    public void save(Project pr) throws IOException {
        LOG.debug("Save project: {}", pr);
        Properties props = new Properties();
        props.setProperty(ID, pr.getId());
        props.setProperty(NAME, pr.getName());
        props.setProperty(DEFAULT_COMMAND, pr.getCommand());
        final FileWriter writer = new FileWriter(new File(pr.getFolder(), prIdFilename));
        props.store(writer, "Example Catalog Project ID file");
        writer.close();
    }

    public Project load(File prDir) throws IOException {
        Properties props = new Properties();
        final FileReader reader = new FileReader(new File(prDir, prIdFilename));
        props.load(reader);
        Project pr = new Project(props.getProperty(ID), prDir.getName(), prDir, props.getProperty(DEFAULT_COMMAND));
        reader.close();
        LOG.debug("Project loaded: {}", pr);
        return pr;
    }
}
