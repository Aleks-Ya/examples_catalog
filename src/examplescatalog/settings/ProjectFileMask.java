package examplescatalog.settings;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Маска имени файла проекта.
 */
public class ProjectFileMask {
    @XmlAttribute
    private String mask;
    @XmlAttribute
    private int priority;

    public String getMask() {
        return mask;
    }

    public int getPriority() {
        return priority;
    }
}
