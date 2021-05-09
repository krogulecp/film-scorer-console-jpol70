package pl.sdacademy.filmscorer.domain;

import pl.sdacademy.filmscorer.infrastructure.DuplicatedKeyException;

import java.util.List;
import java.util.Optional;

public class FilmService {

    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public void addFilm(String title, int releaseYear) {
        try {
            filmRepository.saveOrThrowIfExists(new Film(title, releaseYear));
        } catch (DuplicatedKeyException exception){
            throw new DuplicatedFilmException("Trying to add film that already exists", exception);
        }
    }

    public Optional<Film> getFilm(String title, int releaseYear) {
        return filmRepository.findByTitleAndReleaseYear(title, releaseYear);
    }

    public List<Film> getFilmsByTitle(String title) {
        return filmRepository.findByTitle(title);
    }

    public List<Film> getFilmsByReleaseYear(int releaseYear) {
        //TODO dodać walidację na rok - powinien on być w odpowiednim zakresie -> od 1900 do bieżącego roku
        return filmRepository.findFilmByReleaseYear(releaseYear);
    }
}
