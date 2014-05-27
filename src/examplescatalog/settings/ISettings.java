package examplescatalog.settings;

import java.util.List;

/**
 * Настройки приложения.
 */
public interface ISettings {
    int getPort();

    List<PrFileMask> getPrFileMasks();

    String getPrIdFilename();

    String getExamplesRoot();

    String getIntellijIdeaPath();

    String getDefCmd();
}
