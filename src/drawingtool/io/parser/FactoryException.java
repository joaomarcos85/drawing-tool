package drawingtool.io.parser;

/**
 *
 * @author Joao
 */
public class FactoryException extends Exception {

    public FactoryException(Throwable cause) {
        super(cause);
    }

    public FactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public FactoryException(String message) {
        super(message);
    }
}
