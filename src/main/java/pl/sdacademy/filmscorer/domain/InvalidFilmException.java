package pl.sdacademy.filmscorer.domain;

public class InvalidFilmException extends RuntimeException {
    public InvalidFilmException(String message) {
        super(message);
    }
}
