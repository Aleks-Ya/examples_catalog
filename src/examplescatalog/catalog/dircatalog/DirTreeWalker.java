package examplescatalog.catalog.dircatalog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Обходит дерево каталогов в поисках проектных файлов.
 * //todo Добавить исключения для папок .git, classes, out, .gradle., .idea
 */
class DirTreeWalker {
    private static final DirFileFilter dirFileFilter = new DirFileFilter();
    private File rootDir;
    private ProjectFileFilter projectFileFilter;
    private ProjectIdFileFilter projectIdFileFilter;
    private String projectIdFilename;
    private String defaultCommand;
    private List<Folder> folderList = new ArrayList<>();

    public DirTreeWalker(File rootDir, ProjectFileFilter projectFileFilter, ProjectIdFileFilter projectIdFileFilter,
                         String projectIdFilename, String defaultCommand) {
        this.rootDir = rootDir;
        this.projectFileFilter = projectFileFilter;
        this.projectIdFileFilter = projectIdFileFilter;
        this.projectIdFilename = projectIdFilename;
        this.defaultCommand = defaultCommand;
    }

    public void process() {
        process(rootDir);
    }

    public List<Folder> getFolderList() {
        return folderList;
    }

    private void process(File dir) {
        try {
            Folder folder = new Folder(dir, projectFileFilter, projectIdFileFilter, projectIdFilename, defaultCommand);
            if (folder.isProjectFolder()) {
                folderList.add(folder);
            }

            File[] subDirs = dir.listFiles(dirFileFilter);
            for (File subDir : subDirs) {
                process(subDir);
            }
        } catch (IOException e) {
            //todo логгер
            e.printStackTrace();
        }
    }
}