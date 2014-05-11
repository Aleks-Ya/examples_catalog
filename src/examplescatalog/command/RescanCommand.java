package examplescatalog.command;

import examplescatalog.catalog.Catalog;
import examplescatalog.catalog.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Команда "Пересканировать каталог проектов".
 * todo Реализовать RescanCommand
 */
@Component
class RescanCommand implements ICommand {
    @Autowired
    private Catalog catalog;

    @Override
    public void execute(Project project) {
//        catalog.rescan();
    }
}
