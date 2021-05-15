package pl.sdacademy.filmscorer.infrastructure;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import pl.sdacademy.filmscorer.domain.Film;
import pl.sdacademy.filmscorer.domain.FilmRepository;

import javax.persistence.PersistenceException;
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
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(FilmEntity.fromDomain(film));
            session.getTransaction().commit();
        } catch (PersistenceException e){
            throw new DuplicatedKeyException("Próba dodania filmu o istniejącym id: " + e.getMessage());
        }
    }

    @Override
    public List<Film> findAll() {
        try (Session session = sessionFactory.openSession()) {
            final Query<FilmEntity> query = session.createQuery("SELECT fe FROM FilmEntity fe", FilmEntity.class);
            return query.getResultStream()
                    .map(FilmEntity::toFilm)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Film> findByTitleAndReleaseYear(String title, int releaseYear) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            final Query<FilmEntity> query = session.createQuery("SELECT fe FROM FilmEntity fe WHERE fe.id =?1", FilmEntity.class);
            final FilmEntity filmEntity = query.setParameter(1, title + releaseYear).getSingleResult();
            return Optional.of(filmEntity.toFilm());
        } catch (Exception e) {
            System.out.println(e);
            return Optional.empty();
        }
    }

    @Override
    public List<Film> findByTitle(String title) {
        try (Session session = sessionFactory.openSession()) {
            final Query<FilmEntity> query = session.createQuery("SELECT fe FROM FilmEntity fe WHERE fe.title=?1", FilmEntity.class);
            query.setParameter(1, title);
            return query.getResultStream()
                    .map(FilmEntity::toFilm)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e);
            return Collections.emptyList();
        }
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
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(FilmEntity.fromDomain(film));
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void deleteAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM FilmEntity", FilmEntity.class).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
