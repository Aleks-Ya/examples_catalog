package examplescatalog.server;

/**
 * Ошибка в работе сервера.
 */
class ServerException extends Exception {
    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerException(String message) {
        super(message);
    }
}
