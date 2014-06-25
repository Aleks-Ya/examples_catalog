package examplescatalog.settings;

import examplescatalog.MaskConfig;
import examplescatalog.catalog.CatalogTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class XmlSettingsTest {

    private static AnnotationConfigApplicationContext context;

    @BeforeClass
    public void beforeClass() {
        context = new AnnotationConfigApplicationContext();
//        context.getEnvironment().addActiveProfile(CatalogTest.TEST_PROFILE);
        context.register(EnvironmentConfig.class);
        context.register(SettingsConfig.class);
        context.refresh();
    }

    @Test
    public void excludes() throws Exception {
        ISettings sut = (ISettings) context.getBean("xmlSettings");
        List<FileMask> excludes = sut.getExcludes();
        assertEquals(excludes.size(), 6);
    }
}