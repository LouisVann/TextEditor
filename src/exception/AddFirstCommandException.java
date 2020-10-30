package exception;

public class AddFirstCommandException extends AbstractCommandException {
    public AddFirstCommandException(String message) {
        super(AddFirstCommandException.class.getName() + ": " + message);
    }
}
