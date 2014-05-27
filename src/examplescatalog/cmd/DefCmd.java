package examplescatalog.cmd;

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
class DefCmd implements ICmd {
    private static final Logger LOG = LoggerFactory.getLogger(DefCmd.class);

    @Autowired
    private Map<String, ICmd> cmdMap;

    @Value("#{settings.defCmd}")
    private String defCmdCode;

    @Override
    public void execute(Project project) throws CmdException {
        if (project != null) {
            ICmd cmdToExecute = cmdMap.get(defCmdCode);
            ICmd cmd = cmdMap.get(project.getCmd());
            if (cmd != null) {
                cmdToExecute = cmd;
            }
            cmdToExecute.execute(project);
        } else {
            LOG.error("Project is null.");
        }
    }
}