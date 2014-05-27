package examplescatalog.command;

/**
 * Исключение при обработке команды.
 */
public class CommandException extends Exception {
    public CommandException(Throwable cause) {
        super(cause);
    }

    public CommandException(String message, Object... args) {
        super(String.format(message, args));
    }
}