package examplescatalog.application;

import examplescatalog.settings.ISettings;
import examplescatalog.settings.SettingsException;
import examplescatalog.settings.XmlSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Загружает старый конфигурационный файл xml-файл Spring.
 */
@Configuration
@ImportResource("classpath:examplescatalog/application/spring.xml")
public class Config {
//    @Bean(name = "settings")
//    public ISettings getEnvironmentSettings() throws SettingsException {
//        return XmlSettings.getInstance("settings.xml", "settings.xsd");
//    }

    @Bean(name = "xmlSettings")
    public ISettings getXmlSettings() throws SettingsException {
        String xmlFile = XmlSettings.class.getResource("settings.xml").getFile();
        String xsdFile = XmlSettings.class.getResource("settings.xsd").getFile();
        return XmlSettings.getInstance(xmlFile, xsdFile);
    }
}
