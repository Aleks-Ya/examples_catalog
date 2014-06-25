package examplescatalog.settings;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class XmlSettingsTest {

    private ISettings sut;

    @BeforeClass
    public void beforeClass() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(EnvironmentConfig.class);
        context.register(SettingsConfig.class);
        context.refresh();
        sut = (ISettings) context.getBean("xmlSettings");
    }

    @Test
    public void excludes() throws Exception {
        List<FileMask> excludes = sut.getExcludes();
        assertEquals(excludes.size(), 6);
        for (FileMask mask : excludes) {
            assertTrue(StringUtils.isNotEmpty(mask.getMask()));
        }
    }

    @Test
    public void prFileMasks() throws Exception {
        List<PrFileMask> prFileMasks = sut.getPrFileMasks();
        assertEquals(prFileMasks.size(), 3);
        for (PrFileMask mask : prFileMasks) {
            assertTrue(StringUtils.isNotEmpty(mask.getMask()));
            assertNotNull(mask.getPrDirType());
            assertTrue(mask.getPriority() >= 0);
        }
    }
}