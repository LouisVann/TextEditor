package exception;

public class TextException extends Exception {
    public TextException(String message) {
        super(TextException.class.getName() + ": " + message);
    }
}
