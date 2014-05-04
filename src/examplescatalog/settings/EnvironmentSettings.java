package examplescatalog.settings;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Читает настройки из переменных окружения.
 */
public class EnvironmentSettings implements ISettings {
    private int port;
    private String projectIdFilename;
    private List<String> masks;
    private String intellijIdeaPath;
    private String examplesRoot;

    /**
     * @param xmlSettings                 Настройки, загруженные из xml (будем их переопределять).
     * @param examplesRootEnvironment     Имя переменной окружения с путем к папке каталога примеров.
     * @param intellijIdeaPathEnvironment Имя переменной окружения с путем к исполняемому файлу IntellijIdea.
     */
    public EnvironmentSettings(ISettings xmlSettings, String examplesRootEnvironment, String intellijIdeaPathEnvironment) {
        port = xmlSettings.getPort();
        projectIdFilename = xmlSettings.getProjectIdFilename();
        masks = xmlSettings.getProjectFileMasks();

        String exampleRootEnv = System.getenv(examplesRootEnvironment);
        examplesRoot = (StringUtils.isEmpty(exampleRootEnv)) ? xmlSettings.getExamplesRoot() : exampleRootEnv;

        String intellijIdeaPathEnv = System.getenv(intellijIdeaPathEnvironment);
        intellijIdeaPath = (StringUtils.isEmpty(intellijIdeaPathEnv)) ? xmlSettings.getIntellijIdeaPath() : intellijIdeaPathEnv;
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

    @Override
    public String getExamplesRoot() {
        return examplesRoot;
    }

    @Override
    public String getIntellijIdeaPath() {
        return intellijIdeaPath;
    }
}