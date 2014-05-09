package examplescatalog.catalog.filesystem;

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

public class ProjectFolderListTest {

    @Test
    public void testGetProjectFolders() throws Exception {
        ProjectFileFilter projectFileFilter = initMasks();

        File tempRootDir = initCatalogDir();

        tempRootDir.toString();
    }

    private File initCatalogDir() throws IOException {
        File tempRootDir = Files.createTempDirectory("ExamplesCatalog_Temp_").toFile();

        File subDir1 = new File(tempRootDir, "subDir1");
        createFile(subDir1, "build.gradle");
        createFile(subDir1, "context.xml");
        createFile(new File(subDir1, "src"), "Main.java");

        File subDir2 = new File(tempRootDir, "subDir2");
        createFile(subDir2, "fuck.iml");

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