package examplescatalog.catalog.dircatalog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Обходит дерево каталогов в поисках проектных файлов.
 */
class DirTreeWalker {
    private static final DirFileFilter dirFileFilter = new DirFileFilter();
    private File rootDir;
    private ProjectFileFilter projectFileFilter;
    private List<Project> projectList = new ArrayList<>();
    private Map<String, Project> projectMap = new HashMap<>();

    public DirTreeWalker(File rootDir, ProjectFileFilter projectFileFilter) {
        this.rootDir = rootDir;
        this.projectFileFilter = projectFileFilter;
    }

    public void process() {
        process(rootDir);
        fillProjectMap();
    }

    private void fillProjectMap() {
        for (Project project : projectList) {
            projectMap.put(project.getId(), project);
        }
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public Map<String, Project> getProjectMap() {
        return projectMap;
    }

    private void process(File dir) {
        File[] projectFiles = dir.listFiles(projectFileFilter);
        if (projectFiles.length > 0) {
            projectList.add(new Project(dir));
        }

        File[] subDirs = dir.listFiles(dirFileFilter);
        for (File subDir : subDirs) {
            process(subDir);
        }
    }
}