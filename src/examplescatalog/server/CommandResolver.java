package examplescatalog.server;

import examplescatalog.command.CommandException;
import examplescatalog.command.ICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Создает объект ICommand, соответствующий http-запросу.
 */
@Component
class CommandResolver {

    @Autowired
    private Map<String, ICommand> commandMap;

    @Autowired
    private ApplicationContext context;

    ICommand getCommand(String target, HttpServletRequest request) throws CommandException {
        String cmdCode = request.getParameter("cmd");

        if (commandMap.containsKey(cmdCode)) {
            return commandMap.get(cmdCode);
        } else {
            cmdCode = target.replace("/", "");
            if (commandMap.containsKey(cmdCode)) {
                return commandMap.get(cmdCode);
            } else {
                return context.getBean("defaultCommand", ICommand.class);
            }
        }
    }
}