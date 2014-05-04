package examplescatalog.settings;

/**
 * Ошибка при работе с настройками приложения.
 */
public class SettingsException extends Exception {
    public SettingsException(String message, Throwable cause) {
        super(message, cause);
    }
}
