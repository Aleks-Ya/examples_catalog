package examplescatalog.application;

import examplescatalog.catalog.ICatalog;
import examplescatalog.settings.ISettings;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Главный класс для запуска приложения.
 */
public class ExamplesCatalog {
    public static void main(String[] args) {
        ApplicationContext context = new GenericXmlApplicationContext(ExamplesCatalog.class, "spring.xml");
        ISettings settings = (ISettings) context.getBean("settings");
        ICatalog catalog = (ICatalog) context.getBean("catalog");
    }
}
