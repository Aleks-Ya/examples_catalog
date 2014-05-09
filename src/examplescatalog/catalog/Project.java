package examplescatalog.catalog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Properties;

/**
 * Пример из каталога примеров.
 */
public class Project {
    private static final Logger LOG = LoggerFactory.getLogger(Project.class);
    private String id;
    private String name;
    private File folder;
    private String command;

    public Project(File folder) {
        this.folder = folder;
        name = folder.getName();
        LOG.debug("Created: {}", this);
    }

    public Project(String id, String name, File folder, String command) {
        this.id = id;
        this.name = name;
        this.folder = folder;
        this.command = command;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public File getFolder() {
        return folder;
    }

    public String getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return String.format("Project[id=%s name=%s folder=%s]", id, name, folder.getAbsolutePath());
    }
}