package pl.sdacademy.filmscorer.infrastructure;

import pl.sdacademy.filmscorer.domain.Film;
import pl.sdacademy.filmscorer.domain.FilmRepository;

public class InMemoryFilmRepository implements FilmRepository {
    @Override
    public void saveOrThrowIfExists(Film film) {
        throw new RuntimeException("Not implemented yet!!!");
    }
}
