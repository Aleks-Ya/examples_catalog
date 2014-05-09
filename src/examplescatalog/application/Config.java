package examplescatalog.application;

import examplescatalog.settings.ISettings;
import examplescatalog.settings.SettingsException;
import examplescatalog.settings.XmlSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.Desktop;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Конфигурация бинов, которые не удалось получить напрямую через аннотации.
 */
@Configuration
public class Config {

    @Bean
    public ISettings getSettings() throws SettingsException {
        String xmlFile = XmlSettings.class.getResource("settings.xml").getFile();
        String xsdFile = XmlSettings.class.getResource("settings.xsd").getFile();
        return XmlSettings.getInstance(xmlFile, xsdFile);
    }

    @Bean
    public Executor getExecutor() {
        return Executors.newFixedThreadPool(10);
    }

    @Bean
    public Desktop getDesktop() {
        return Desktop.getDesktop();
    }
}
