package exception;

public class ClientException extends Exception {
    public ClientException(String message) {
        super(ClientException.class.getName() + ": " + message);
    }
}
