package pl.sdacademy.filmscorer.domain;

public interface FilmRepository {
    void saveOrThrowIfExists(Film film);
}
