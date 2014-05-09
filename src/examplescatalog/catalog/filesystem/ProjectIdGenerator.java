package examplescatalog.catalog.filesystem;

import examplescatalog.catalog.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Генератор индетификаторов для проектов.
 */
class ProjectIdGenerator {
    private final List<Integer> projectIdList = new ArrayList<>();
    private static final Random RANDOM = new Random();

    public void setProjectsWithId(List<Project> prWithId) {
        for (Project pr : prWithId) {
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
