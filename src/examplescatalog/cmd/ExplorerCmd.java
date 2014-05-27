package examplescatalog.cmd;

import examplescatalog.catalog.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 * Команда "Открыть проект в проводнике".
 */
@Component
class ExplorerCmd implements ICmd {
    private static final Logger LOG = LoggerFactory.getLogger(ExplorerCmd.class);
    @Value("#{T(java.awt.Desktop).desktop}")
    private Desktop desktop;

    @Override
    public void execute(Project project) {
        File folder = project.getFolder();
        LOG.info("Open folder in explorer: {}", folder.getAbsolutePath());
        try {
            desktop.open(folder);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
