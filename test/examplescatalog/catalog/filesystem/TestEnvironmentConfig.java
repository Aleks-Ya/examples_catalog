package examplescatalog.catalog.filesystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Конфигурация переменных окружения.
 */
@Configuration
@Profile(PrFolderListTest.TEST_PROFILE)
class TestEnvironmentConfig {

    @Bean(name = "envMap")
    public Map<String, String> getEnvironmentMap() throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("EXAMPLES_ROOT", initCatalogDir().getAbsolutePath());
        return map;
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

    private void createFile(File dir, String name) throws IOException {
        dir.mkdirs();
        new FileWriter(new File(dir, name)).close();
    }

    private void createPropertiesFile(File dir, String name, Properties properties) throws IOException {
        dir.mkdirs();
        properties.store(new FileWriter(new File(dir, name)), "Exmples Catalog Test");
    }
}