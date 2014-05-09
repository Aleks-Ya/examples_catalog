package examplescatalog.catalog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Каталог примеров, хранимый в памяти.
 */
public class MemoryCatalog implements ICatalog {
    private Map<String, Project> projects = new HashMap<>();

    public MemoryCatalog(ICatalog baseCatalog) {
        for (Project project : baseCatalog.getAllProjects()) {
            projects.put(project.getId(), project);
        }
    }

    @Override
    public Project getPrById(String projectId) {
        return projects.get(projectId);
    }

    @Override
    public List<Project> getAllProjects() {
        return new ArrayList<>(projects.values());
    }

    @Override
    public void addProject(Project project) {
        throw new UnsupportedOperationException();
    }
}
