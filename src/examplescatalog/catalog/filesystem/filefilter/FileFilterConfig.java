package examplescatalog.catalog.filesystem.filefilter;

import examplescatalog.application.ExamplesCatalog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.FileFilter;
import java.io.FilenameFilter;

/**
 * Конфигурация бинов FileFilter.
 */
@Configuration
class FileFilterConfig {
    @Bean(name = "dirFileFilter")
    public FileFilter getDirFF() {
        return new DirFileFilter();
    }

    @Bean(name = "prFileFilter")
    public FilenameFilter getPrFF() {
        return new PrFileFilter();
    }

    @Bean(name = "prIdFileFilter")
    public FilenameFilter getPrIdFF() {
        return new PrIdFileFilter();
    }
}
