package examplescatalog.command;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.Desktop;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Конфигурация бинов для пакета command.
 */
@Configuration
class CommandConfig {

    @Bean
    public Desktop getDesktop() {
        return Desktop.getDesktop();
    }
}
