package examplescatalog.catalog;

import examplescatalog.catalog.dircatalog.Project;

import java.util.List;

/**
 * Каталог примеров.
 */
public interface ICatalog {
    Project getProjectById(String projectId);

    List<Project> getAllProjects();
}