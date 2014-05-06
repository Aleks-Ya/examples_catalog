package examplescatalog.command;

import examplescatalog.catalog.Project;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 * Команда "Открыть проект в проводнике".
 */
public class ExplorerCommand implements ICommand {
    private Desktop desktop;

    public ExplorerCommand(Desktop desktop) {
        this.desktop = desktop;
    }

    @Override
    public void execute(Project project) {
        File folder = project.getFolder();
        try {
            desktop.open(folder);
        } catch (IOException e) {
            //todo логгер
            e.printStackTrace();
        }
    }
}
