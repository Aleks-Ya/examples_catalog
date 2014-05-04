package examplescatalog.settings;

import java.util.List;

/**
 * Читает настройки из переменных окружения.
 */
public class EnvironmentSettings implements ISettings {
    /**
     * @param xmlSettings                 Настройки, загруженные из xml (будем их переопределять).
     * @param examplesRootEnvironment     Имя переменной окружения с путем к папке каталога примеров.
     * @param intellijIdeaPathEnvironment Имя переменной окружения с путем к исполняемому файлу IntellijIdea.
     */
    public EnvironmentSettings(ISettings xmlSettings, String examplesRootEnvironment, String intellijIdeaPathEnvironment) {
    }

    @Override
    public int getPort() {
        return 0;
    }

    @Override
    public List<String> getProjectFileMasks() {
        return null;
    }

    @Override
    public String getProjectIdFilename() {
        return null;
    }
}
