package examplescatalog.cmd;

import examplescatalog.catalog.Pr;
import org.springframework.stereotype.Component;

/**
 * Команда "Открыть проект в Idea".
 * todo Реализовать IdeaCmd
 */
@Component
class IdeaCmd implements ICmd {
    @Override
    public void execute(Pr pr) {
        throw new UnsupportedOperationException();
    }
}
