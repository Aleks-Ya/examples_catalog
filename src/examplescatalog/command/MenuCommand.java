package examplescatalog.command;

import examplescatalog.catalog.Project;
import org.springframework.stereotype.Component;

/**
 * Команда "Открыть меню для выбора другой команды".
 * todo Реализовать в виде html-меню на открывшейся странице браузера
 */
@Component
public class MenuCommand implements ICommand {
    @Override
    public void execute(Project project) {
        throw new UnsupportedOperationException();
    }
}
