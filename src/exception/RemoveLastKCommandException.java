package exception;

public class RemoveLastKCommandException extends AbstractCommandException {
    public RemoveLastKCommandException(String message) {
        super(RemoveLastKCommandException.class.getName() + ": " + message);
    }
}
