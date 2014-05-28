package examplescatalog;

import examplescatalog.catalog.CatalogTest;
import examplescatalog.settings.PrFileMask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

import java.lang.reflect.Field;

/**
 * Конфигурация переменных окружения.
 */
@Configuration
@Profile(CatalogTest.TEST_PROFILE)
public class MaskConfig {

    @Bean
    @Scope("prototype")
    public PrFileMask imlMask(int priority) throws NoSuchFieldException, IllegalAccessException {
        return makeMask(".*.iml", priority);
    }

    @Bean
    @Scope("prototype")
    public PrFileMask mavenMask(int priority) throws NoSuchFieldException, IllegalAccessException {
        return makeMask("pom.xml", priority);
    }

    @Bean
    @Scope("prototype")
    public PrFileMask gradleMask(int priority) throws NoSuchFieldException, IllegalAccessException {
        return makeMask(".*.gradle", priority);
    }

    private PrFileMask makeMask(String maskStr, int priority) throws NoSuchFieldException, IllegalAccessException {
        Field maskField = PrFileMask.class.getDeclaredField("mask");
        maskField.setAccessible(true);
        Field priorityField = PrFileMask.class.getDeclaredField("priority");
        priorityField.setAccessible(true);

        PrFileMask imlMask = new PrFileMask();
        priorityField.set(imlMask, priority);
        maskField.set(imlMask, maskStr);
        return imlMask;
    }
}