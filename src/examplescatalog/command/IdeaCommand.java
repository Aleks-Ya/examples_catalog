package examplescatalog.command;

import examplescatalog.catalog.Project;
import org.springframework.stereotype.Component;

/**
 * Команда "Открыть проект в Idea".
 * todo Реализовать IdeaCommand
 */
@Component
class IdeaCommand implements ICommand {
    @Override
    public void execute(Project project) {
        throw new UnsupportedOperationException();
    }
}
