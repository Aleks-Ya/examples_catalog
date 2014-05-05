package examplescatalog.application;

import examplescatalog.catalog.ICatalog;
import examplescatalog.server.Server;
import examplescatalog.settings.ISettings;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Главный класс для запуска приложения.
 * todo Добавить логирование
 */
public class ExamplesCatalog {
    public static void main(String[] args) {
        ApplicationContext context = new GenericXmlApplicationContext(ExamplesCatalog.class, "spring.xml");
        ISettings settings = context.getBean("settings", ISettings.class);
        ICatalog catalog = context.getBean("catalog", ICatalog.class);
        Server server = context.getBean("server", Server.class);
    }
}
