package examplescatalog.catalog.filesystem;

import examplescatalog.catalog.Project;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DEFAULT_COMMAND = "default_command";
    private String prIdFilename;

    @Autowired
    public PrSaver(
            @Value("#{settings.projectIdFilename}") String prIdFilename) {
        this.prIdFilename = prIdFilename;
    }

    public void save(Project pr) throws IOException {
        Properties props = new Properties();
        props.setProperty(ID, pr.getId());
        props.setProperty(NAME, pr.getName());
        props.setProperty(DEFAULT_COMMAND, pr.getCommand());
        props.store(new FileWriter(new File(pr.getFolder(), prIdFilename)), "Example Catalog Project ID file");
    }

    public Project load(File prDir) throws IOException {
        Properties props = new Properties();
        props.load(new FileReader(new File(prDir, prIdFilename)));
        return new Project(props.getProperty(ID), prDir.getName(), prDir, props.getProperty(DEFAULT_COMMAND));
    }
}
