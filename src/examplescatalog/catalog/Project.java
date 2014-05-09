package examplescatalog.catalog;

import java.io.File;

/**
 * Пример из каталога примеров.
 */
public class Project {
    private String id;
    private String name;
    private File folder;
    private String command;

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