package examplescatalog.cmd;

import examplescatalog.catalog.Project;
import org.springframework.stereotype.Component;

/**
 * Команда "Открыть проект в Idea".
 * todo Реализовать IdeaCmd
 */
@Component
class IdeaCmd implements ICmd {
    @Override
    public void execute(Project project) {
        throw new UnsupportedOperationException();
    }
}
