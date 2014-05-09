package examplescatalog.command;

import examplescatalog.catalog.Project;
import org.springframework.stereotype.Component;

/**
 * Команда "Открыть проект в Idea".
 */
@Component
public class IdeaCommand implements ICommand {
    @Override
    public void execute(Project project) {
        throw new UnsupportedOperationException();
    }
}
