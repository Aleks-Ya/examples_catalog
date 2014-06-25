package examplescatalog.settings;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.File;
import java.util.regex.Pattern;

/**
 * Маска имени файла проекта.
 */
public class FileMask {
    @XmlAttribute
    private String mask;

    private Pattern pattern;

    public String getMask() {
        return mask;
    }

    /**
     * Подпадает ли данный файл под маску?
     */
    public boolean accept(File prFile) {
        if (pattern == null) {
            pattern = Pattern.compile(getMask());
        }
        return pattern.matcher(prFile.getName()).matches();
    }
}