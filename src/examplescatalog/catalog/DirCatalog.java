package examplescatalog.catalog;

import java.util.List;

/**
 * Каталог примеров, читаемый из файловой системы.
 * todo Читать в отдельном потоке
 */
public class DirCatalog implements ICatalog {
    @Override
    public Project getProjectById(String projectId) {
        return null;
    }

    @Override
    public List<Project> getAllProjects() {
        return null;
    }
}
