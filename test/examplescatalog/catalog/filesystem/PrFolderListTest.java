package examplescatalog.catalog.filesystem;

import examplescatalog.catalog.Catalog;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PrFolderListTest {
    public static final String TEST_PROFILE = "test";

    //todo удалить временную папку после теста
    @Test
    public void testGetProjectFolders() throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().addActiveProfile(TEST_PROFILE);
        context.scan("examplescatalog.settings");
        context.scan("examplescatalog.catalog");
        context.refresh();

        Catalog catalog = context.getBean(Catalog.class);
        assertEquals(catalog.getAllProjects().size(), 3);
    }
}