package exception;

public abstract class AbstractCommandException extends Exception {
    public AbstractCommandException(String message) {
        super(message);
    }
}
