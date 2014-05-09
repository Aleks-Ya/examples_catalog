package examplescatalog.catalog.filesystem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Составляет 2 списка файлов проект:
 * 1. С идентификационным файлом проекта;
 * 2. Без идентификационного файла проекта.
 */
public class ProjectIdList {
    private List<File> projectWithIdFile = new ArrayList<>();
    private List<File> projectWithoutIdFile = new ArrayList<>();
    private ProjectFolderList projectFolderList;
    private ProjectIdFileFilter projectIdFileFilter;

    public ProjectIdList(ProjectFolderList projectFolderList, ProjectIdFileFilter projectIdFileFilter) {
        this.projectFolderList = projectFolderList;
        this.projectIdFileFilter = projectIdFileFilter;
        init();
    }

    private void init() {
        for (File projectFolder : projectFolderList.getProjectFolders()) {
            if (projectFolder.listFiles(projectIdFileFilter).length > 0) {
                projectWithIdFile.add(projectFolder);
            } else {
                projectWithoutIdFile.add(projectFolder);
            }
        }
    }

    public List<File> getProjectWithIdFile() {
        return projectWithIdFile;
    }

    public List<File> getProjectWithoutIdFile() {
        return projectWithoutIdFile;
    }
}
