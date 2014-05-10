package examplescatalog.server;

import examplescatalog.application.ExamplesCatalog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Конфигурация бинов для пакета server.
 */
@Configuration
class ServerConfig {

    @Bean
    public Executor getExecutor() {
        return Executors.newFixedThreadPool(10);
    }
}
