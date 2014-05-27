package examplescatalog.cmd;

/**
 * Исключение при обработке команды.
 */
public class CmdException extends Exception {
    public CmdException(Throwable cause) {
        super(cause);
    }

    public CmdException(String message, Object... args) {
        super(String.format(message, args));
    }
}