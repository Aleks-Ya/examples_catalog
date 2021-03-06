package examplescatalog.catalog;

import java.io.File;

/**
 * Пример из каталога примеров.
 */
public class Pr {
    private String id;
    private String name;
    private File folder;
    private String cmd;

    public Pr(String id, String name, File folder, String cmd) {
        this.id = id;
        this.name = name;
        this.folder = folder;
        this.cmd = cmd;
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

    public String getCmd() {
        return cmd;
    }

    @Override
    public String toString() {
        return String.format("Project[id=%s name=%s folder=%s]", id, name, folder.getAbsolutePath());
    }
}