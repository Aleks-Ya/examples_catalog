package examplescatalog.settings;

import examplescatalog.application.ExamplesCatalog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.HashMap;
import java.util.Map;

/**
 * Конфигурация переменных окружения.
 */
@Configuration
@Profile(ExamplesCatalog.RUN_PROFILE)
class EnvironmentConfig {

    @Bean(name = "envMap")
    public Map<String, String> getEnvironmentMap() {
        return new HashMap(System.getenv());
    }
}
