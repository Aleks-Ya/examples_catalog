package examplescatalog.settings;

import examplescatalog.MaskConfig;
import examplescatalog.catalog.CatalogTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Field;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class FileMaskTest {

    private static AnnotationConfigApplicationContext context;

    @BeforeClass
    public void beforeClass() throws NoSuchFieldException {
        context = new AnnotationConfigApplicationContext();
        context.getEnvironment().addActiveProfile(CatalogTest.TEST_PROFILE);
        context.register(MaskConfig.class);
        context.refresh();

        Field maskField = FileMask.class.getDeclaredField("mask");
        maskField.setAccessible(true);
    }

    @Test
    public void accept() throws Exception {
        FileMask imlMask = (FileMask) context.getBean("imlMask", 0);
        FileMask mavenMask = (FileMask) context.getBean("mavenMask", 0);
        FileMask gradleMask = (FileMask) context.getBean("gradleMask", 0);
        FileMask gitMask = (FileMask) context.getBean("gitMask");

        File imlFile = new File("dir/ExemplesCatalog.iml");
        File gradleFile = new File("dir/build.gradle");
        File mavenFile = new File("dir/pom.xml");
        File javaFile = new File("Main.java");
        File gitFile = new File(".git");

        assertTrue(imlMask.accept(imlFile));
        assertFalse(imlMask.accept(gradleFile));
        assertFalse(imlMask.accept(mavenFile));
        assertFalse(imlMask.accept(javaFile));
        assertFalse(imlMask.accept(gitFile));

        assertFalse(gradleMask.accept(imlFile));
        assertTrue(gradleMask.accept(gradleFile));
        assertFalse(gradleMask.accept(mavenFile));
        assertFalse(gradleMask.accept(javaFile));
        assertFalse(gradleMask.accept(gitFile));

        assertFalse(mavenMask.accept(imlFile));
        assertFalse(mavenMask.accept(gradleFile));
        assertTrue(mavenMask.accept(mavenFile));
        assertFalse(mavenMask.accept(javaFile));
        assertFalse(mavenMask.accept(gitFile));

        assertTrue(gitMask.accept(gitFile));
        assertFalse(gitMask.accept(imlFile));
        assertFalse(gitMask.accept(gradleFile));
        assertFalse(gitMask.accept(mavenFile));
        assertFalse(gitMask.accept(javaFile));
    }
}