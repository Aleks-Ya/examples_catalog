package examplescatalog.catalog.dircatalog;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * Идентификационный файл проекта.
 */
class ProjectIdFile {
    private static final String ID_PROPERTY = "id";
    private static final String DEFAULT_COMMAND_PROPERTY = "default_command";
    private String id;
    private String name;
    private String defaultCommand;

    /**
     * Создает новый идентификационный файл проекта.
     */
    public ProjectIdFile(String projectId, File projectFile, String defaultCommand) throws IOException {
        Properties properties = new Properties();
        properties.setProperty(ID_PROPERTY, projectId);
        properties.setProperty(DEFAULT_COMMAND_PROPERTY, defaultCommand);
        properties.store(new FileWriter(projectFile), "Examples Catalog Project Id File");
//todo Бросать исключение если один из параметров null или пустой
        this.id = projectId;
        this.defaultCommand = defaultCommand;
        this.name = projectFile.getName();
    }

    /**
     * Читает существующий идентификационный файл проекта.
     */
    public ProjectIdFile(File projectFile) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(projectFile));
//todo Бросать исключение если один из параметров null или пустой
        this.id = properties.getProperty(ID_PROPERTY);
        this.defaultCommand = properties.getProperty(DEFAULT_COMMAND_PROPERTY);
        this.name = projectFile.getName();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDefaultCommand() {
        return defaultCommand;
    }
}
