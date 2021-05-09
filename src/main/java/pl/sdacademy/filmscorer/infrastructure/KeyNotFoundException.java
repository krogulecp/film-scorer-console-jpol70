package pl.sdacademy.filmscorer.infrastructure;

public class KeyNotFoundException extends RuntimeException {
    public KeyNotFoundException(String message) {
        super(message);
    }
}
