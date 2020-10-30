package exception;

public class ShowCommandException extends AbstractCommandException {
    public ShowCommandException(String message) {
        super(ShowCommandException.class.getName() + ": " + message);
    }
}
