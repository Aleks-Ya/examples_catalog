package examplescatalog.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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