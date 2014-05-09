package examplescatalog.catalog;

import examplescatalog.catalog.Project;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Каталог примеров.
 */
@Component
public class Catalog {
    private Map<String, Project> projects = new HashMap<>();

    public Project getPrById(String projectId) {
        return projects.get(projectId);
    }

    public List<Project> getAllProjects() {
        return new ArrayList<>(projects.values());
    }

    public void addProject(Project project) {
        projects.put(project.getId(), project);
    }
}