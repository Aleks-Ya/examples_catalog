package examplescatalog.command;

import examplescatalog.catalog.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Команда вызывает другую команду, указанную как команда по-умолчанию в идентификационном файле проект.а
 */
@Component
class DefaultCommand implements ICommand {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultCommand.class);

    @Autowired
    private Map<String, ICommand> cmdMap;

    @Value("#{settings.defaultCommand}")
    private String defCmdCode;

    @Override
    public void execute(Project project) throws CommandException {
        if (project != null) {
            ICommand cmdToExecute = cmdMap.get(defCmdCode);
            ICommand cmd = cmdMap.get(project.getCommand());
            if (cmd != null) {
                cmdToExecute = cmd;
            }
            cmdToExecute.execute(project);
        } else {
            LOG.error("Project is null.");
        }
    }
}