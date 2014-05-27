package examplescatalog.cmd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Конфигурация для пакета cmd.
 */
@Configuration
class CmdConfig {

    @Bean
    @Scope("prototype")
    ICmd defCmd() {
        return new DefCmd();
    }
}