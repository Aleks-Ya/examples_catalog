package examplescatalog.settings;

import examplescatalog.application.ExamplesCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Конфигурация бинов для пакета settings.
 */
@Configuration
class SettingsConfig {

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
}
