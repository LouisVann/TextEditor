package exception;

public class AddLastCommandException extends AbstractCommandException {
    public AddLastCommandException(String message) {
        super(AddLastCommandException.class.getName() + ": " + message);
    }
}
