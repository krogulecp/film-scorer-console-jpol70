package pl.sdacademy.filmscorer.domain;

import pl.sdacademy.filmscorer.infrastructure.DuplicatedKeyException;

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

    public Film getFilm(String title, int releaseYear) {
        //TODO
        return null;
    }
}
