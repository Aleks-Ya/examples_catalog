package examplescatalog.catalog.filesystem;

import examplescatalog.catalog.Catalog;
import examplescatalog.settings.PrFileMask;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class PrFolderListTest {
    //удалить временную папку после теста
    @Test
    public void testGetProjectFolders() throws Exception {
        File tempRootDir = initCatalogDir();
        DirFileFilter dirFileFilter = new DirFileFilter();
        PrFileFilter prFileFilter = initMasks();

        PrFolderList folderList = new PrFolderList(tempRootDir.getAbsolutePath(), dirFileFilter, prFileFilter);
        folderList.process();

        assertEquals(folderList.getProjectFolders().size(), 3);

        final String prIdFilename = "ExamplesCatalog.properties";
        PrIdFileFilter prIdFileFilter = new PrIdFileFilter(prIdFilename);

        PrIdList prIdList = new PrIdList(folderList, prIdFileFilter);
        List<File> prWithId = prIdList.getPrWithIdFile();
        List<File> prWithoutId = prIdList.getPrWithoutIdFile();
        assertEquals(prWithId.size(), 1);
        assertEquals(prWithoutId.size(), 2);

        PrSaver prSaver = new PrSaver(prIdFilename);

        PrFactoryExist prFactoryExist = new PrFactoryExist(prIdList, prSaver);
        Catalog catalog = prFactoryExist.getCatalog();

        assertEquals(catalog.getAllProjects().size(), 1);

        PrIdGenerator prIdGenerator = new PrIdGenerator(catalog.getAllProjects());

        PrFactoryNotExist prFactoryNotExist = new PrFactoryNotExist("explorerCommand", prSaver, catalog, prIdGenerator);
        prFactoryNotExist.createProjects(prWithoutId);

        assertEquals(catalog.getAllProjects().size(), 3);
    }

    private File initCatalogDir() throws IOException {
        File tempRootDir = Files.createTempDirectory("ExamplesCatalog_Temp_").toFile();

        File subDir1 = new File(tempRootDir, "subDir1");
        createFile(subDir1, "build.gradle");
        createFile(subDir1, "context.xml");
        createFile(new File(subDir1, "src"), "Main.java");

        File subDir2 = new File(tempRootDir, "subDir2");
        createFile(subDir2, "fuck.iml");

        Properties properties = new Properties();
        properties.setProperty("id", "123");
        properties.setProperty("name", "SubDir2");
        properties.setProperty("default_command", "explorerCommand");

        createPropertiesFile(subDir2, "ExamplesCatalog.properties", properties);

        File subDir2_1 = new File(subDir2, "sub_project");
        createFile(subDir2_1, "good.iml");
        return tempRootDir;
    }

    private PrFileFilter initMasks() {
        PrFileMask gradle = mock(PrFileMask.class);
        when(gradle.getMask()).thenReturn(".*.gradle");

        PrFileMask idea = mock(PrFileMask.class);
        when(idea.getMask()).thenReturn(".*.iml");

        List<PrFileMask> masks = Arrays.asList(gradle, idea);
        return new PrFileFilter(masks);
    }

    private void createFile(File dir, String name) throws IOException {
        dir.mkdirs();
        new FileWriter(new File(dir, name)).close();
    }

    private void createPropertiesFile(File dir, String name, Properties properties) throws IOException {
        dir.mkdirs();
        properties.store(new FileWriter(new File(dir, name)), "Exmples Catalog Test");
    }
}