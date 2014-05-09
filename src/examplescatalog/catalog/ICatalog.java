package examplescatalog.catalog;

import java.util.List;

/**
 * Каталог примеров.
 */
public interface ICatalog {
    Project getPrById(String projectId);

    List<Project> getAllProjects();

    void addProject(Project project);
}