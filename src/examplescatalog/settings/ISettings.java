package examplescatalog.settings;

import java.util.List;

/**
 * Настройки приложения.
 */
public interface ISettings {
    int getPort();
    List<String> getProjectFileMasks();
    String getProjectIdFilename();
}
