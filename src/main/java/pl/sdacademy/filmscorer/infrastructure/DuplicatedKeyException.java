package pl.sdacademy.filmscorer.infrastructure;

public class DuplicatedKeyException extends RuntimeException {

    public DuplicatedKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}
