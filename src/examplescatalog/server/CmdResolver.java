package examplescatalog.server;

import examplescatalog.cmd.CmdException;
import examplescatalog.cmd.ICmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Создает объект ICmd, соответствующий http-запросу.
 */
@Component
class CmdResolver {

    @Autowired
    private Map<String, ICmd> cmdMap;

    @Autowired
    private ApplicationContext context;

    ICmd getCmd(String target, HttpServletRequest request) throws CmdException {
        String cmdCode = request.getParameter("cmd");

        if (cmdMap.containsKey(cmdCode)) {
            return cmdMap.get(cmdCode);
        } else {
            cmdCode = target.replace("/", "");
            if (cmdMap.containsKey(cmdCode)) {
                return cmdMap.get(cmdCode);
            } else {
                return context.getBean("defCmd", ICmd.class);
            }
        }
    }
}