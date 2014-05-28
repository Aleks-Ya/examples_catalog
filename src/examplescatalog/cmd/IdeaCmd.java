package examplescatalog.cmd;

import examplescatalog.catalog.Pr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Команда "Открыть проект в Idea".
 */
@Component("idea")
class IdeaCmd implements ICmd {
    private static final Logger LOG = LoggerFactory.getLogger(IdeaCmd.class);

    private String ideaExecutable;

    @Autowired
    private PrFileResolver prFileResolver;

    @Autowired
    IdeaCmd(@Value("#{settings.intellijIdeaPath}") String ideaPath) {
        if (ideaPath == null) {
            throw new IllegalArgumentException("Intellij Idea path is null");
        }
        File ideaDir = new File(ideaPath);
        File ideaSh = new File(ideaDir, "bin/idea.sh");
        File ideaExe = new File(ideaDir, "bin/idea.exe");
        ideaExecutable = (ideaSh.exists()) ? ideaSh.getAbsolutePath() : ideaExe.getAbsolutePath();
    }

    @Override
    public void execute(Pr pr) throws CmdException {
        try {
            LOG.info("Open project in Idea: {}", pr.getFolder().getAbsolutePath());
            String prFile = prFileResolver.getAppropriatePrFile(pr.getFolder()).getAbsolutePath();
            Runtime.getRuntime().exec(String.format("/bin/sh %s %s", ideaExecutable, prFile));
        } catch (IOException e) {
            throw new CmdException(e);
        }
    }
}