package examplescatalog.settings;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Загружает настройки из xml-файла.
 */
@XmlRootElement(name = "settings")
@SuppressWarnings("unused")
class XmlSettings implements ISettings {
    @XmlAttribute
    private int port;
    @XmlAttribute
    private String projectIdFilename;
    @XmlAttribute
    private String defaultCommand;
    @XmlElementWrapper(name = "projectFileMasks")
    @XmlElement(name = "mask")
    private List<PrFileMask> masks;

    public static XmlSettings getInstance(String xmlSettingsFile, String xsdFile) throws SettingsException {
        File xml = new File(xmlSettingsFile);
        File xsd = new File(xsdFile);
        try {
            validate(xml, xsd);
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlSettings.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (XmlSettings) jaxbUnmarshaller.unmarshal(xml);
        } catch (IOException | SAXException | JAXBException e) {
            throw new SettingsException("Read xml-file error", e);
        }
    }

    private static void validate(File xmlFile, File xsd) throws IOException, SettingsException, SAXException {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(xsd);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlFile);
            validator.validate(source);
        } catch (SAXParseException e) {
            throw new SettingsException("XML not valid", e);
        }
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public List<PrFileMask> getProjectFileMasks() {
        return masks;
    }

    @Override
    public String getProjectIdFilename() {
        return projectIdFilename;
    }

    @Override
    public String getExamplesRoot() {
        return null;// будет добавлено в EnvironmentSettings
    }

    @Override
    public String getIntellijIdeaPath() {
        return null;// будет добавлено в EnvironmentSettings
    }

    @Override
    public String getDefaultCommand() {
        return defaultCommand;
    }
}
