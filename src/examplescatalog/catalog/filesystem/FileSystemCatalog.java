package examplescatalog.catalog.filesystem;

import examplescatalog.catalog.ICatalog;
import examplescatalog.catalog.Project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Читает каталог проектов из файловой системы.
 */
public class FileSystemCatalog implements ICatalog {
    private Map<String, Project> projects = new HashMap<>();

    @Override
    public Project getProjectById(String projectId) {
        return projects.get(projectId);
    }

    @Override
    public List<Project> getAllProjects() {
        return new ArrayList<>(projects.values());
    }

    @Override
    public void addProject(Project project) {
        projects.put(project.getId(), project);
    }
}
