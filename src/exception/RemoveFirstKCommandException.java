package exception;

public class RemoveFirstKCommandException extends AbstractCommandException {
    public RemoveFirstKCommandException(String message) {
        super(RemoveFirstKCommandException.class.getName() + ": " + message);
    }
}
