package examplescatalog.application;

import examplescatalog.settings.EnvironmentSettings;
import examplescatalog.settings.ISettings;
import examplescatalog.settings.SettingsException;
import examplescatalog.settings.XmlSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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

    @Autowired
    ApplicationContext context;

    @Bean(name = "settings")
    public ISettings getEnvironmentSettings() throws SettingsException {
        return context.getBean(EnvironmentSettings.class);
    }

    @Bean(name = "xmlSettings")
    public ISettings getXmlSettings() throws SettingsException {
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
