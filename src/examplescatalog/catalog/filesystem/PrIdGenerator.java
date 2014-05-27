package examplescatalog.catalog.filesystem;

import examplescatalog.catalog.Catalog;
import examplescatalog.catalog.Pr;
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
class PrIdGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(PrIdGenerator.class);
    private final List<Integer> prIdList = new ArrayList<>();
    private static final Random RANDOM = new Random();
    @Autowired
    private Catalog catalog;

    public void generate() {
        for (Pr pr : catalog.getAllPrs()) {
            prIdList.add(Integer.valueOf(pr.getId()));
        }
    }

    public String generateId() {
        Integer next;
        do {
            next = Math.abs(RANDOM.nextInt());
        } while (prIdList.contains(next));
        prIdList.add(next);
        LOG.debug("Generated project id: {}", next);
        return next.toString();
    }
}