package examplescatalog.catalog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Пример из каталога примеров.
 */
public class Project {
    private static final Logger LOG = LoggerFactory.getLogger(Project.class);
    private String id;
    private String name;
    private File folder;

    public Project(File folder, String projectId) {
        this.folder = folder;
        id = projectId;
        name = folder.getName();
        LOG.debug("Created: {}", this);
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

    @Override
    public String toString() {
        return String.format("Project[id=%s name=%s folder=%s]", id, name, folder.getAbsolutePath());
    }
}