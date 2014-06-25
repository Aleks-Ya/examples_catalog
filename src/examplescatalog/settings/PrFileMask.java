package examplescatalog.settings;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Маска имени файла проекта (с приоритетом).
 */
public class PrFileMask extends FileMask implements Comparable<PrFileMask> {
    @XmlAttribute
    private int priority;

    /**
     * В качестве параметра передать Idea ссылку на: 1) папку проекта, 2) файл проекта.
     */
    @XmlAttribute
    private PrDirType prDirType;

    public int getPriority() {
        return priority;
    }

    public PrDirType getPrDirType() {
        return prDirType;
    }

    @Override
    public int compareTo(PrFileMask o) {
        return Integer.valueOf(getPriority()).compareTo(o.getPriority());
    }

    public enum PrDirType {
        FOLDER, FILE
    }
}