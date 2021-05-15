package pl.sdacademy.filmscorer.infrastructure;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import pl.sdacademy.filmscorer.domain.Film;
import pl.sdacademy.filmscorer.domain.FilmRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FilmJpaRepository implements FilmRepository {

    private final SessionFactory sessionFactory;

    public FilmJpaRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveOrThrowIfExists(Film film) {
        //TODO throw exception if key exists
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(FilmEntity.fromDomain(film));
            session.getTransaction().commit();
        } catch (Exception e){
            System.out.println("Błąd podczas zapisu filmu: " + e);
        }
    }

    @Override
    public List<Film> findAll() {
        //TODO
        return null;
    }

    @Override
    public Optional<Film> findByTitleAndReleaseYear(String title, int releaseYear) {
        //TODO
        return Optional.empty();
    }

    @Override
    public List<Film> findByTitle(String title) {
        //TODO
        return null;
    }

    @Override
    public List<Film> findFilmByReleaseYear(int releaseYear) {
        try (Session session = sessionFactory.openSession()) {
            final Query<FilmEntity> query = session.createQuery("SELECT fe FROM FilmEntity fe WHERE fe.releaseYear=?1", FilmEntity.class);
            query.setParameter(1, releaseYear);
            return query.getResultStream()
                    .map(FilmEntity::toFilm)
                    .collect(Collectors.toList());
        } catch (Exception e){
            System.out.println("Coś poszło nie tak " + e);
            return Collections.emptyList();
        }
    }

    @Override
    public void update(Film film) {
        //TODO
    }

    @Override
    public void deleteAll() {
        //TODO
    }
}
