package pl.sdacademy.filmscorer.domain;

import java.util.List;
import java.util.Optional;

public interface FilmRepository {
    void saveOrThrowIfExists(Film film);
    List<Film> findAll();
    Optional<Film> findByTitleAndReleaseYear(String title, int releaseYear);
    List<Film> findByTitle(String title);
    List<Film> findFilmByReleaseYear(int releaseYear);
}

