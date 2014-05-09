package examplescatalog.catalog.filesystem;

import examplescatalog.catalog.Catalog;
import examplescatalog.catalog.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Генератор индетификаторов для проектов.
 */
@Component
class PrIdGenerator {
    private final List<Integer> projectIdList = new ArrayList<>();
    private static final Random RANDOM = new Random();

    @Autowired
    PrIdGenerator(Catalog catalog) {
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
        return next.toString();
    }
}
