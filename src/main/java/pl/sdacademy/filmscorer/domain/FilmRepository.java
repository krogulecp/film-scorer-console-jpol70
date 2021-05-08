package pl.sdacademy.filmscorer.domain;

import java.util.List;

public interface FilmRepository {
    void saveOrThrowIfExists(Film film);
    List<Film> findAll();
}
