package examplescatalog.command;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Конфигурация для пакета command.
 */
@Configuration
class CommandConfig {

    @Bean
    @Scope("prototype")
    ICommand defaultCommand() {
        return new DefaultCommand();
    }
}