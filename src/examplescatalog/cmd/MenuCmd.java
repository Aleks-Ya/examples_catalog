package examplescatalog.cmd;

import examplescatalog.catalog.Project;
import org.springframework.stereotype.Component;

/**
 * Команда "Открыть меню для выбора другой команды".
 * todo Реализовать в виде html-меню на открывшейся странице браузера
 */
@Component
class MenuCmd implements ICmd {
    @Override
    public void execute(Project project) {
        throw new UnsupportedOperationException();
    }
}
