package rest.example.exceptions;

public class MainNotFoundException extends RuntimeException {
    public MainNotFoundException(String message) {
        super(message);
    }
}
