package pl.sdacademy.filmscorer.infrastructure;

import pl.sdacademy.filmscorer.domain.Film;
import pl.sdacademy.filmscorer.domain.FilmRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryFilmRepository implements FilmRepository {

    private static final Map<String, Film> films = new HashMap<>();

    @Override
    public void saveOrThrowIfExists(Film film) {
        final String key = constructKey(film);
        final Film foundFilm = films.get(key);
        if (foundFilm == null) {
            films.put(key, film);
        } else {
            throw new DuplicatedKeyException("Film with title " + film.getTitle() + " released in " + film.getReleaseYear() + " already exists!");
        }
    }

    @Override
    public List<Film> findAll() {
        //TODO to implement
        return null;
    }

    private String constructKey(Film film) {
        return film.getTitle() + film.getReleaseYear();
    }
}
