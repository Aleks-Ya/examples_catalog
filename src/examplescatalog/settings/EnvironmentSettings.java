package examplescatalog.settings;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * Читает настройки из переменных окружения.
 */
@Component
class EnvironmentSettings implements ISettings {
    private int port;
    private String projectIdFilename;
    private String defaultCommand;
    private List<PrFileMask> masks;
    private String intellijIdeaPath;
    private String examplesRoot;
    @Autowired
    @Qualifier("xmlSettings")
    private ISettings parent;
    @Value("EXAMPLES_ROOT")
    private String examplesRootEnvironment;
    @Value("INTELLIJ_IDEA_PATH")
    private String intellijIdeaPathEnvironment;
    @Value("#{envMap}")
    private Map<String, String> envMap;

    @PostConstruct
    private void init() {
        port = parent.getPort();
        projectIdFilename = parent.getProjectIdFilename();
        defaultCommand = parent.getDefaultCommand();
        masks = parent.getProjectFileMasks();

        String exampleRootEnv = envMap.get(examplesRootEnvironment);
        examplesRoot = (StringUtils.isEmpty(exampleRootEnv)) ? parent.getExamplesRoot() : exampleRootEnv;

        String intellijIdeaPathEnv = envMap.get(intellijIdeaPathEnvironment);
        intellijIdeaPath = (StringUtils.isEmpty(intellijIdeaPathEnv)) ? parent.getIntellijIdeaPath() : intellijIdeaPathEnv;
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