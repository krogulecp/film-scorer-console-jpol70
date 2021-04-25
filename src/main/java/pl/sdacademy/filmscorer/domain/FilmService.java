package pl.sdacademy.filmscorer.domain;

public class FilmService {

    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public void addFilm(String title, int releaseYear) {
        try {
            filmRepository.saveOrThrowIfExists(new Film(title, releaseYear));
        } catch (Exception exception){
            throw new DuplicatedFilmException("Trying to add film that already exists", exception);
        }
    }
}