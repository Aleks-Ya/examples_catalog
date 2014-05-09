package examplescatalog.settings;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Читает настройки из переменных окружения.
 */
@Component
public class EnvironmentSettings implements ISettings {
    private int port;
    private String projectIdFilename;
    private String defaultCommand;
    private List<PrFileMask> masks;
    private String intellijIdeaPath;
    private String examplesRoot;
    @Autowired
    @Qualifier("xmlSettings")
    private ISettings xmlSettings;
    @Value("EXAMPLES_ROOT")
    private String examplesRootEnvironment;
    @Value("INTELLIJ_IDEA_PATH")
    private String intellijIdeaPathEnvironment;

    @PostConstruct
    private void init() {
        port = xmlSettings.getPort();
        projectIdFilename = xmlSettings.getProjectIdFilename();
        defaultCommand = xmlSettings.getDefaultCommand();
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
    public List<PrFileMask> getProjectFileMasks() {
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

    @Override
    public String getDefaultCommand() {
        return defaultCommand;
    }
}