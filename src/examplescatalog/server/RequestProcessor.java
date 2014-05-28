package examplescatalog.server;

import examplescatalog.catalog.Catalog;
import examplescatalog.catalog.Pr;
import examplescatalog.cmd.CmdException;
import examplescatalog.cmd.ICmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * Обработчик запроса к серверу.
 */
@Component
@Scope("prototype")
class RequestProcessor implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(RequestProcessor.class);
    private String target;
    private HttpServletRequest request;

    @Autowired
    private Catalog catalog;

    @Autowired
    private CmdResolver cmdResolver;

    @Autowired
    private PrResolver prResolver;

    @Autowired
    RequestProcessor(String target, HttpServletRequest request) {
        this.target = target;
        this.request = request;
    }

    @Override
    public void run() {
        try {
            while (!catalog.isReady()) {
                LOG.warn("Wait catalog ready");
                TimeUnit.SECONDS.sleep(1);
            }

            ICmd cmd = cmdResolver.getCmd(target, request);
            Pr pr = prResolver.getPr(target, request);
            cmd.execute(pr);
        } catch (CmdException | InterruptedException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}