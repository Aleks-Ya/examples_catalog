package examplescatalog.catalog.filesystem.filefilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileFilter;
import java.io.FilenameFilter;

/**
 * Конфигурация бинов FileFilter.
 */
@Configuration
class FileFilterConfig {
    @Autowired
    private ApplicationContext context;

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
