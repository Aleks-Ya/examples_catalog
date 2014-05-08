package examplescatalog.catalog.dircatalog;

import examplescatalog.catalog.Project;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * Папка на диске.
 */
class Folder {
    private static final Logger LOG = LoggerFactory.getLogger(Folder.class);
    private File dir;
    private FilenameFilter projectFileFilter;
    private ProjectIdFileFilter projectIdFileFilter;
    private String projectIdFilename;
    private String defaultCommand;
    private ProjectIdFile projectIdFile;

    public Folder(File dir, FilenameFilter projectFileFilter, ProjectIdFileFilter projectIdFileFilter,
                  String projectIdFilename, String defaultCommand) throws IOException {
        this.dir = dir;
        this.projectFileFilter = projectFileFilter;
        this.projectIdFileFilter = projectIdFileFilter;
        this.projectIdFilename = projectIdFilename;
        this.defaultCommand = defaultCommand;

        File[] projectIdFiles = dir.listFiles(projectIdFileFilter);
        projectIdFile = (projectIdFiles.length > 0) ? new ProjectIdFile(projectIdFiles[0]) : null;
    }

    /**
     * Данная папка содержит проект примера?
     */
    public boolean isProjectFolder() {
        return ArrayUtils.isEmpty(dir.listFiles(projectFileFilter));
    }

    /**
     * В проектной папке есть идентификационный файл проекта?
     */
    public boolean hasProjectIdFile() {
        return projectIdFile != null;
    }

    public void createProjectIdFile(String projectId) throws IOException {
        LOG.info("Create project id file in {}", dir.getAbsolutePath());
        File newProjectIdFile = new File(dir, projectIdFilename);
        projectIdFile = new ProjectIdFile(projectId, newProjectIdFile, defaultCommand);
    }

    public ProjectIdFile getProjectIdFile() {
        return projectIdFile;
    }

    public Project getProject() {
        return new Project(dir, getProjectIdFile().getId());
    }

//    public List<File> getProjectFiles() {
//        List<File> result = new ArrayList<>();
//        for (File projectFile : dir.listFiles(projectFileFilter)) {
//            result.add(new Project(dir, projectFile));
//        }
//        return result;
//    }
}