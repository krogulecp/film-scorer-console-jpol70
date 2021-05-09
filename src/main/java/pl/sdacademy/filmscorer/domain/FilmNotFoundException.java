package pl.sdacademy.filmscorer.domain;

public class FilmNotFoundException extends RuntimeException {
    public FilmNotFoundException(String message) {
        super(message);
    }
}
