package examplescatalog.catalog.dircatalog;

import examplescatalog.catalog.Project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Плоский список проектов в каталоге.
 * Также овечает за создание идентификационных файлов в новых проектах.
 */
class ProjectList {
    private final List<Project> projectList = new ArrayList<>();
    private final Map<String, Project> projectMap = new HashMap<>();

    public ProjectList(DirTreeWalker walker) throws IOException {
        //todo инициализировать генератор только, если найден проект без идентификационного файла
        ProjectIdGenerator generator = new ProjectIdGenerator(walker.getFolderList());

        for (Folder folder : walker.getFolderList()) {
            if (!folder.hasProjectIdFile()) {
                folder.createProjectIdFile(generator.generateId());
            }
            Project project = folder.getProject();
            projectList.add(project);
            projectMap.put(project.getId(), project);
        }
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public Map<String, Project> getProjectMap() {
        return projectMap;
    }
}
