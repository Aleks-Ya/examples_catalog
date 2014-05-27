package examplescatalog.server;

import examplescatalog.catalog.Catalog;
import examplescatalog.catalog.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Определяет проект, соответствующий http-запросу.
 */
@Component
class PrResolver {

    @Autowired
    private Catalog catalog;

    Project getProject(String target, HttpServletRequest request) {
        String prParameterCode = request.getParameter("pr");
        Project pr = catalog.getPrById(prParameterCode);
        if (pr != null) {
            return pr;
        } else {
            String prPathCode = target.replace("/", "");
            return catalog.getPrById(prPathCode);
        }
    }
}