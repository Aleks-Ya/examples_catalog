package examplescatalog.catalog;

import java.util.List;

/**
 * Каталог примеров.
 */
public interface ICatalog {
    Project getProjectById(String projectId);

    List<Project> getAllProjects();
}