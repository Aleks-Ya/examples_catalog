package examplescatalog.catalog;

import examplescatalog.catalog.filesystem.PrFolderScanner;
import examplescatalog.settings.ISettings;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.assertEquals;

public class CatalogTest {
    public static final String TEST_PROFILE = "test";
    private static AnnotationConfigApplicationContext context;

    @BeforeClass
    public void beforeClass() {
        context = new AnnotationConfigApplicationContext();
        context.getEnvironment().addActiveProfile(TEST_PROFILE);
        context.scan("examplescatalog.settings");
        context.scan("examplescatalog.catalog");
        context.refresh();
    }

    @Test
    public void testGetPrFolders() throws Exception {
        PrFolderScanner scanner = context.getBean(PrFolderScanner.class);
        scanner.scan();
        Catalog catalog = context.getBean(Catalog.class);
        assertEquals(catalog.getAllPrs().size(), 3);
    }

    @AfterClass
    public void afterClass() {
        ISettings settings = context.getBean("settings", ISettings.class);
        File rootDir = new File(settings.getExamplesRoot());
        deleteTree(rootDir);
    }

    private void deleteTree(File... files) {
        for (File file : files) {
            if (file.isDirectory()) {
                deleteTree(file.listFiles());
            }
            file.delete();
        }
    }
}