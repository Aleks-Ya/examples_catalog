package examplescatalog.catalog.filesystem;

import examplescatalog.catalog.Pr;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * Читает и записывает идентификационный файл проекта.
 */
@Component
class PrSaver {
    private static final Logger LOG = LoggerFactory.getLogger(PrSaver.class);
    private static final String ID = "id";
    private static final String DEF_CMD = "def_cmd";

    @Value("#{settings.prIdFilename}")
    private String prIdFilename;

    void save(Pr pr) throws IOException {
        LOG.debug("Save project: {}", pr);
        Properties props = new Properties();
        props.setProperty(ID, pr.getId());
        String cmd = pr.getCmd();
        if (!StringUtils.isEmpty(cmd)) {
            props.setProperty(DEF_CMD, cmd);
        }
        final FileWriter writer = new FileWriter(new File(pr.getFolder(), prIdFilename));
        props.store(writer, "Example Catalog Project ID file");
        writer.close();
    }

    Pr load(File prDir) throws IOException {
        Properties props = new Properties();
        final FileReader reader = new FileReader(new File(prDir, prIdFilename));
        props.load(reader);
        Pr pr = new Pr(props.getProperty(ID), prDir.getName(), prDir, props.getProperty(DEF_CMD));
        reader.close();
        LOG.debug("Project loaded: {}", pr);
        return pr;
    }
}