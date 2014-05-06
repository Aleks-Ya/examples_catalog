package examplescatalog.catalog.dircatalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Генератор индетификаторов для проектов.
 */
class ProjectIdGenerator {
    private final List<Long> projectIdList = new ArrayList<>();
    private static final Random RANDOM = new Random();

    public ProjectIdGenerator(List<Folder> folderList) {
        for (Folder folder : folderList) {
            if (folder.hasProjectIdFile()) {
                projectIdList.add(Long.valueOf(folder.getProjectIdFile().getId()));
            }
        }
    }

    public String generateId() {
        Long next;
        do {
            next = Math.abs(RANDOM.nextLong());
        } while (projectIdList.contains(next));
        projectIdList.add(next);
        return next.toString();
    }
}
