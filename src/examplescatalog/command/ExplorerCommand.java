package examplescatalog.command;

import examplescatalog.catalog.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 * Команда "Открыть проект в проводнике".
 */
@Component
class ExplorerCommand implements ICommand {
    private static final Logger LOG = LoggerFactory.getLogger(ExplorerCommand.class);
    @Autowired
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
