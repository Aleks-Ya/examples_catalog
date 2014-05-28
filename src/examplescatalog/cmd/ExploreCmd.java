package examplescatalog.cmd;

import examplescatalog.catalog.Pr;
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
@Component("explore")
class ExploreCmd implements ICmd {
    private static final Logger LOG = LoggerFactory.getLogger(ExploreCmd.class);
    @Value("#{T(java.awt.Desktop).desktop}")
    private Desktop desktop;

    @Override
    public void execute(Pr pr) {
        File folder = pr.getFolder();
        LOG.info("Open folder in explorer: {}", folder.getAbsolutePath());
        try {
            desktop.open(folder);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
