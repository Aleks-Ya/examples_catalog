package examplescatalog.server;

import examplescatalog.catalog.Catalog;
import examplescatalog.command.ICommand;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * Обработчик http-запроса, поступившего к Jetty.
 */
@Component
class RequestHander extends AbstractHandler {
    @Autowired
    private Executor executor;

    @Autowired
    private Catalog catalog;

    @Autowired
    private Map<String, ICommand> commandMap;

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        response.getWriter().printf("<h1>Handled %s </h1>", target);

        executor.execute(new RequestProcessor(target, request, commandMap, catalog));
    }
}