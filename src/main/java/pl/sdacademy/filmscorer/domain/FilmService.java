package pl.sdacademy.filmscorer.domain;

import pl.sdacademy.filmscorer.infrastructure.DuplicatedKeyException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class FilmService {

    private final FilmRepository filmRepository;
    private final FilmScorer filmScorer;

    public FilmService(FilmRepository filmRepository, FilmScorer filmScorer) {
        this.filmRepository = filmRepository;
        this.filmScorer = filmScorer;
    }

    public void addFilm(String title, int releaseYear) {
        try {
            filmRepository.saveOrThrowIfExists(new Film(title, releaseYear));
        } catch (DuplicatedKeyException exception){
            throw new DuplicatedFilmException("Trying to add film that already exists", exception);
        } catch (Exception e){
            System.out.println("");
        }
    }

    public Optional<Film> getFilm(String title, int releaseYear) {
        return filmRepository.findByTitleAndReleaseYear(title, releaseYear);
    }

    public List<Film> getFilmsByTitle(String title) {
        return filmRepository.findByTitle(title);
    }

    public List<Film> getFilmsByReleaseYear(int releaseYear) {
        if (releaseYear < 1900 || releaseYear > LocalDate.now().getYear()){
            throw new InvalidFilmException("Podany rok produkcji jest niepoprawny");
        }
        return filmRepository.findFilmByReleaseYear(releaseYear);
    }

    public void addFilmScore(String title, int releaseYear, int score) {
        //znaleźć film o danym tytule i roku produkcji
        //obsłużyć przypadek, kiedy chcemy dodać ocenę dla nieistniejącego filmu
        if (score < 1 || score > 10){
            throw new InvalidScoreException("Ocena musi znajdować się w przedziale od 1 do 10");
        }
        final Film film = filmRepository.findByTitleAndReleaseYear(title, releaseYear)
                .orElseThrow(() -> new FilmNotFoundException("Nie znaleziono filmu " + title + " Brak możliwości oceny!!!"));
        final Score newScore = filmScorer.calculate(film.getScore(), score);
        film.setScore(newScore);
        //chcemy na koniec zapisać w repozytorium film ze zmianami
        filmRepository.update(film);
    }
}
