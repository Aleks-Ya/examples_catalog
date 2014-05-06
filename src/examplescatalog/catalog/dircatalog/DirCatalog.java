package examplescatalog.catalog.dircatalog;

import examplescatalog.catalog.ICatalog;
import examplescatalog.catalog.Project;

import java.util.List;

/**
 * Каталог примеров, читаемый из файловой системы.
 * todo Читать в отдельном потоке
 */
public class DirCatalog implements ICatalog {
    private ProjectList projectList;

    public DirCatalog(ProjectList projectList) {
        this.projectList = projectList;
    }

    @Override
    public Project getProjectById(String projectId) {
        return projectList.getProjectMap().get(projectId);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectList.getProjectList();
    }
}