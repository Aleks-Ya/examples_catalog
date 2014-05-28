package examplescatalog.settings;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.File;
import java.util.regex.Pattern;

/**
 * Маска имени файла проекта.
 */
public class PrFileMask implements Comparable<PrFileMask> {
    @XmlAttribute
    private String mask;
    @XmlAttribute
    private int priority;

    /**
     * В качестве параметра передать Idea ссылку на: 1) папку проекта, 2) файл проекта.
     */
    @XmlAttribute
    private PrDirType prDirType;

    private Pattern pattern;

    public int getPriority() {
        return priority;
    }

    public PrDirType getPrDirType() {
        return prDirType;
    }

    /**
     * Подпадает ли данный файл под маску?
     */
    public boolean accept(File prFile) {
        if (pattern == null) {
            pattern = Pattern.compile(mask);
        }
        return pattern.matcher(prFile.getName()).matches();
    }

    @Override
    public int compareTo(PrFileMask o) {
        return Integer.valueOf(getPriority()).compareTo(o.getPriority());
    }

    public enum PrDirType {
        FOLDER, FILE;
    }
}