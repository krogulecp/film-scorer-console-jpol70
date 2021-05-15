package pl.sdacademy.filmscorer.infrastructure;

import org.hibernate.SessionFactory;
import pl.sdacademy.filmscorer.domain.Film;
import pl.sdacademy.filmscorer.domain.FilmRepository;

import java.util.List;
import java.util.Optional;

public class FilmJpaRepository implements FilmRepository {

    private final SessionFactory sessionFactory;

    public FilmJpaRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveOrThrowIfExists(Film film) {

    }

    @Override
    public List<Film> findAll() {
        return null;
    }

    @Override
    public Optional<Film> findByTitleAndReleaseYear(String title, int releaseYear) {
        return Optional.empty();
    }

    @Override
    public List<Film> findByTitle(String title) {
        return null;
    }

    @Override
    public List<Film> findFilmByReleaseYear(int releaseYear) {
        return null;
    }

    @Override
    public void update(Film film) {

    }

    @Override
    public void deleteAll() {

    }
}
