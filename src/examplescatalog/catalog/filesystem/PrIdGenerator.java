package examplescatalog.catalog.filesystem;

import examplescatalog.catalog.Catalog;
import examplescatalog.catalog.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Генератор индетификаторов для проектов.
 */
@Component()
public class PrIdGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(PrIdGenerator.class);
    private final List<Integer> projectIdList = new ArrayList<>();
    private static final Random RANDOM = new Random();
    @Autowired
    private Catalog catalog;

    public void init() {
        for (Project pr : catalog.getAllProjects()) {
            projectIdList.add(Integer.valueOf(pr.getId()));
        }
    }

    public String generateId() {
        Integer next;
        do {
            next = Math.abs(RANDOM.nextInt());
        } while (projectIdList.contains(next));
        projectIdList.add(next);
        LOG.debug("Generated project id: {}", next);
        return next.toString();
    }
}
