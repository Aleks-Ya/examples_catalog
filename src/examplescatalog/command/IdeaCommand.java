package examplescatalog.command;

import examplescatalog.catalog.dircatalog.Project;

/**
 * Команда "Открыть проект в Idea".
 */
public class IdeaCommand implements ICommand {
    @Override
    public void execute(Project project) {
        throw new UnsupportedOperationException();
    }
}
