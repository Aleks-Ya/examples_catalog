package examplescatalog.catalog;

import java.io.File;

/**
 * Пример из каталога примеров.
 */
public class Project {
    private String id;
    private String name;
    private File folder;

    public Project(File folder, String projectId) {
        this.folder = folder;
        id = projectId;
        name = folder.getName();
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
}