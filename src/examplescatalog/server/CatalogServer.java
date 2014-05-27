package examplescatalog.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Сервер слушает порт, принимает http-запросы и вызывает соответствующие команды.
 */
@Component
public class CatalogServer {
    private static final Logger LOG = LoggerFactory.getLogger(CatalogServer.class);
    @Value("#{settings.port}")
    private int port;

    @Autowired
    private RequestHander hander;

    public void start() {
        LOG.info("Server started");
        try {
            ContextHandler context = new ContextHandler("/");
            context.setHandler(hander);

            Server jetty = new Server(port);
            jetty.setHandler(context);
            jetty.start();
            jetty.join();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}