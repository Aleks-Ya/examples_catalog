package examplescatalog.catalog.filesystem;

import examplescatalog.catalog.ICatalog;
import examplescatalog.settings.ProjectFileMask;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class ProjectFolderListTest {
    //удалить временную папку после теста
    @Test
    public void testGetProjectFolders() throws Exception {
        File tempRootDir = initCatalogDir();
        DirFileFilter dirFileFilter = new DirFileFilter();
        ProjectFileFilter projectFileFilter = initMasks();

        ProjectFolderList folderList = new ProjectFolderList(tempRootDir.getAbsolutePath(), dirFileFilter, projectFileFilter);
        folderList.process();

        assertEquals(folderList.getProjectFolders().size(), 3);

        final String projectIdFilename = "ExamplesCatalog.properties";
        ProjectIdFileFilter projectIdFileFilter = new ProjectIdFileFilter(projectIdFilename);

        ProjectIdList projectIdList = new ProjectIdList(folderList, projectIdFileFilter);
        List<File> projectWithId = projectIdList.getProjectWithIdFile();
        List<File> projectWithoutId = projectIdList.getProjectWithoutIdFile();
        assertEquals(projectWithId.size(), 1);
        assertEquals(projectWithoutId.size(), 2);

        PrSaver prSaver = new PrSaver(projectIdFilename);

        ExistProjectFactory existProjectFactory = new ExistProjectFactory(projectIdList, prSaver);
        ICatalog catalog = existProjectFactory.getCatalog();

        ProjectIdGenerator projectIdGenerator = new ProjectIdGenerator();
        projectIdGenerator.setProjectsWithId(catalog.getAllProjects());


        DefaultProjectFactory defaultProjectFactory = new DefaultProjectFactory("explorerCommand", prSaver);
        for (File prDir : projectWithoutId) {
            String id = projectIdGenerator.generateId();
            defaultProjectFactory.getInstance(prDir, id);
        }
    }

    private File initCatalogDir() throws IOException {
        File tempRootDir = Files.createTempDirectory("ExamplesCatalog_Temp_").toFile();

        File subDir1 = new File(tempRootDir, "subDir1");
        createFile(subDir1, "build.gradle");
        createFile(subDir1, "context.xml");
        createFile(new File(subDir1, "src"), "Main.java");

        File subDir2 = new File(tempRootDir, "subDir2");
        createFile(subDir2, "fuck.iml");
        createFile(subDir2, "ExamplesCatalog.properties");

        File subDir2_1 = new File(subDir2, "sub_project");
        createFile(subDir2_1, "good.iml");
        return tempRootDir;
    }

    private ProjectFileFilter initMasks() {
        ProjectFileMask gradle = mock(ProjectFileMask.class);
        when(gradle.getMask()).thenReturn(".*.gradle");

        ProjectFileMask idea = mock(ProjectFileMask.class);
        when(idea.getMask()).thenReturn(".*.iml");

        List<ProjectFileMask> masks = Arrays.asList(gradle, idea);
        return new ProjectFileFilter(masks);
    }

    private void createFile(File dir, String name) throws IOException {
        dir.mkdirs();
        new FileWriter(new File(dir, name)).close();
    }
}