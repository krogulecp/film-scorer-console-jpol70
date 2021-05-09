package pl.sdacademy.filmscorer.infrastructure;

import pl.sdacademy.filmscorer.domain.Film;
import pl.sdacademy.filmscorer.domain.FilmRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return new ArrayList<>(films.values());
    }

    @Override
    public Optional<Film> findByTitleAndReleaseYear(String title, int releaseYear) {
        return Optional.ofNullable(films.get(constructKey(title, releaseYear)));
    }

    @Override
    public List<Film> findByTitle(String title) {
        return films.values().stream()
                .filter(film -> film.getTitle().equals(title))
                .collect(Collectors.toList());
    }

    @Override
    public List<Film> findFilmByReleaseYear(int releaseYear) {
        //TODO
        return null;
    }

    private String constructKey(Film film) {
        return film.getTitle() + film.getReleaseYear();
    }

    private String constructKey(String title, int releaseYear){
        return title + releaseYear;
    }
}
