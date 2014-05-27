package examplescatalog.catalog;

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

    /**
     * Каталог обновлен и готов к использованию.
     */
    private boolean isReady = false;

    public Project getPrById(String projectId) {
        return projects.get(projectId);
    }

    public List<Project> getAllProjects() {
        return new ArrayList<>(projects.values());
    }

    public void addProject(Project project) {
        projects.put(project.getId(), project);
    }

    /**
     * Очищает каталог примеров.
     */
    public void clear() {
        projects.clear();
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean isReady) {
        this.isReady = isReady;
    }

}