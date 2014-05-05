package examplescatalog.catalog.dircatalog;

import examplescatalog.catalog.ICatalog;

import java.util.List;

/**
 * Каталог примеров, читаемый из файловой системы.
 * todo Читать в отдельном потоке
 */
public class DirCatalog implements ICatalog {
    private DirTreeWalker walker;

    @Override
    public Project getProjectById(String projectId) {
        return walker.getProjectMap().get(projectId);
    }

    @Override
    public List<Project> getAllProjects() {
        return walker.getProjectList();
    }

    public void setWalker(DirTreeWalker walker) {
        this.walker = walker;
    }
}