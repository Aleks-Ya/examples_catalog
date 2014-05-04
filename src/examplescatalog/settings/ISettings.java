package examplescatalog.settings;

import java.util.List;

/**
 * Настройки приложения.
 */
public interface ISettings {
    int getPort();

    List<ProjectFileMask> getProjectFileMasks();

    String getProjectIdFilename();

    String getExamplesRoot();

    String getIntellijIdeaPath();
}
