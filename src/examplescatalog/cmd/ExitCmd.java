package examplescatalog.cmd;

import examplescatalog.catalog.Pr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Команда "Завершить работу приложения".
 */
@Component
class ExitCmd implements ICmd {
    private static final Logger LOG = LoggerFactory.getLogger(ExitCmd.class);

    @Override
    public void execute(Pr pr) {
        LOG.info("Exit command");
        System.exit(0);
    }
}