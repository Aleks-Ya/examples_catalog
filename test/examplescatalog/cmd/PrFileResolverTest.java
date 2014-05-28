package examplescatalog.cmd;

import examplescatalog.MaskConfig;
import examplescatalog.catalog.CatalogTest;
import examplescatalog.settings.PrFileMask;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class PrFileResolverTest {

    private static AnnotationConfigApplicationContext context;

    private static final File imlFile = new File("dir/ExemplesCatalog.iml");
    private static final File gradleFile = new File("dir/build.gradle");
    private static final File mavenFile = new File("dir/pom.xml");
    private static final File javaFile = new File("Main.java");

    private PrFileMask imlMask;
    private PrFileMask gradleMask;
    private PrFileMask mavenMask;

    private PrFileResolver resolver;

    @BeforeClass
    public void beforeClass() {
        context = new AnnotationConfigApplicationContext();
        context.getEnvironment().addActiveProfile(CatalogTest.TEST_PROFILE);
        context.register(MaskConfig.class);
        context.refresh();

        imlMask = (PrFileMask) context.getBean("imlMask", 3);
        gradleMask = (PrFileMask) context.getBean("gradleMask", 2);
        mavenMask = (PrFileMask) context.getBean("mavenMask", 1);

        resolver = new PrFileResolver(Arrays.asList(imlMask, mavenMask, gradleMask));
    }

    /**
     * Подошел файл с 1ым приоритетом.
     */
    @Test
    public void getAppropriatePrFile1() throws Exception {
        File[] files = new File[]{imlFile, gradleFile, mavenFile, javaFile};
        File prDir = mock(File.class);
        when(prDir.listFiles(any(FileFilter.class))).thenReturn(files);
        assertEquals(resolver.getAppropriatePrFile(prDir), imlFile);
    }

    /**
     * Подошел файл с 2ым приоритетом.
     */
    @Test
    public void getAppropriatePrFile2() throws Exception {
        File[] files = new File[]{gradleFile, mavenFile, javaFile};
        File prDir = mock(File.class);
        when(prDir.listFiles(any(FileFilter.class))).thenReturn(files);
        assertEquals(resolver.getAppropriatePrFile(prDir), gradleFile);
    }

    /**
     * Файл с проектом не найден.
     */
    @Test(expectedExceptions = RuntimeException.class)
    public void notFound() throws Exception {
        File[] files = new File[]{javaFile};
        File prDir = mock(File.class);
        when(prDir.listFiles(any(FileFilter.class))).thenReturn(files);
        assertEquals(resolver.getAppropriatePrFile(prDir), gradleFile);
    }
}