package examplescatalog.server;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.Executor;

/**
 * Обработчик http-запроса, поступившего к Jetty.
 */
@Component
class RequestHander extends AbstractHandler {
    private static final Logger LOG = LoggerFactory.getLogger(RequestHander.class);

    @Autowired
    private Executor executor;

    @Autowired
    private ApplicationContext context;

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        LOG.debug("Received request: target={} parameters={}", target, request.getParameterMap());

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        response.getWriter().printf("<h1>Handled %s </h1>", target);

        executor.execute((Runnable) context.getBean("requestProcessor", target, request));
    }
}