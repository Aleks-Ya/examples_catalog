package examplescatalog.cmd;

import examplescatalog.catalog.Pr;
import org.springframework.stereotype.Component;

/**
 * Команда "Открыть меню для выбора другой команды".
 * todo Реализовать в виде html-меню на открывшейся странице браузера
 */
@Component("menu")
class MenuCmd implements ICmd {
    @Override
    public void execute(Pr pr) {
        throw new UnsupportedOperationException();
    }
}
