package examplescatalog.command;

import examplescatalog.catalog.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 * Команда "Открыть проект в проводнике".
 */
public class ExplorerCommand implements ICommand {
    private static final Logger LOG = LoggerFactory.getLogger(ExplorerCommand.class);
    private Desktop desktop;

    public ExplorerCommand(Desktop desktop) {
        this.desktop = desktop;
    }

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
