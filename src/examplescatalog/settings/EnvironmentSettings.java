package examplescatalog.settings;

import java.util.List;

/**
 * Читает настройки из переменных окружения.
 */
public class EnvironmentSettings implements ISettings {
    public EnvironmentSettings(ISettings xmlSettings) {
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
