package examplescatalog.settings;

import java.util.List;

/**
 * Читает настройки из переменных окружения.
 */
public class EnvironmentSettings implements ISettings {
    private int port;
    private String projectIdFilename;
    private List<String> masks;

    /**
     * @param xmlSettings                 Настройки, загруженные из xml (будем их переопределять).
     * @param examplesRootEnvironment     Имя переменной окружения с путем к папке каталога примеров.
     * @param intellijIdeaPathEnvironment Имя переменной окружения с путем к исполняемому файлу IntellijIdea.
     */
    public EnvironmentSettings(ISettings xmlSettings, String examplesRootEnvironment, String intellijIdeaPathEnvironment) {
        port = xmlSettings.getPort();
        projectIdFilename = xmlSettings.getProjectIdFilename();
        masks = xmlSettings.getProjectFileMasks();
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public List<String> getProjectFileMasks() {
        return masks;
    }

    @Override
    public String getProjectIdFilename() {
        return projectIdFilename;
    }
}
