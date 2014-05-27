package examplescatalog.command;

import examplescatalog.catalog.Project;
import examplescatalog.catalog.filesystem.PrFolderScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Команда "Пересканировать каталог проектов".
 */
@Component
class RescanCommand implements ICommand {
    private static final List<String> NAMES = Arrays.asList("/scan");

    @Autowired
    private PrFolderScanner scanner;

    @Override
    public void execute(Project project) {
        scanner.scan();
    }
}