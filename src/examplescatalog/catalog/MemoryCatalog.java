package examplescatalog.catalog;

import java.util.List;

/**
 * Каталог примеров, хранимый в памяти.
 */
public class MemoryCatalog implements ICatalog {
    @Override
    public Project getProjectById(String projectId) {
        return null;
    }

    @Override
    public List<Project> getAllProjects() {
        return null;
    }
}
