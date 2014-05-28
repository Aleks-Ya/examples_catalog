package examplescatalog.cmd;

import examplescatalog.catalog.Pr;
import examplescatalog.catalog.filesystem.PrFolderScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Команда "Пересканировать каталог проектов".
 */
@Component("rescan")
class RescanCmd implements ICmd {
    private static final Logger LOG = LoggerFactory.getLogger(RescanCmd.class);

    @Autowired
    private PrFolderScanner scanner;

    @Override
    public void execute(Pr pr) throws CmdException {
        LOG.info("Rescan catalog folder");
        scanner.scan();
    }
}