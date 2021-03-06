package pl.sdacademy.filmscorer.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.filmscorer.infrastructure.InMemoryFilmRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FilmServiceIntegrationTest {

    private FilmService filmService;
    private FilmRepository filmRepository;
    private FilmScorer filmScorer;

    @BeforeEach
    void setUp() {
        filmRepository = new InMemoryFilmRepository();
        filmScorer = new SimpleFilmScorer();
        filmService = new FilmService(filmRepository, filmScorer);
    }

    @AfterEach
    void tearDown() {
        filmRepository.deleteAll();
    }

    @Test
    void shouldAddFilm(){
        //when
        filmService.addFilm(SampleFilm.SAMPLE_FILM_TITLE, SampleFilm.SAMPLE_RELEASE_YEAR);

        //then
        final List<Film> foundFilms = filmRepository.findAll();
        assertEquals(1, foundFilms.size());
        final Film foundFilm = foundFilms.get(0);
        assertEquals(SampleFilm.defaultFilm(), foundFilm);
    }

    @Test
    void shouldAddFilmV2(){
        //when
        filmService.addFilm(SampleFilm.SAMPLE_FILM_TITLE, SampleFilm.SAMPLE_RELEASE_YEAR);

        //then
        final Optional<Film> foundFilm = filmService.getFilm(SampleFilm.SAMPLE_FILM_TITLE, SampleFilm.SAMPLE_RELEASE_YEAR);
        Assertions.assertTrue(foundFilm.isPresent());
        assertEquals(SampleFilm.defaultFilm(), foundFilm.get());
    }

    @Test
    void shouldGetFilmsByTitle(){
        //given
        filmService.addFilm(SampleFilm.SAMPLE_FILM_TITLE, SampleFilm.SAMPLE_RELEASE_YEAR);
        filmService.addFilm(SampleFilm.SAMPLE_FILM_TITLE, 1990);
        filmService.addFilm("Green mile", 1998);

        //when
        List<Film> films = filmService.getFilmsByTitle(SampleFilm.SAMPLE_FILM_TITLE);

        //then
        assertEquals(2, films.size());
        assertEquals(SampleFilm.SAMPLE_FILM_TITLE, films.get(0).getTitle());
        assertEquals(SampleFilm.SAMPLE_FILM_TITLE, films.get(1).getTitle());
    }

    @Test
    void shouldGetFilmsByReleaseYear(){
        //given
        //doda?? kilka film??w z r????nymi datami produkcji + dwa z tak?? sam?? dat??
        final int releaseYear = 1980;
        filmService.addFilm(SampleFilm.SAMPLE_FILM_TITLE, releaseYear);
        filmService.addFilm("Git Ekipa", releaseYear);
        filmService.addFilm("Green mile", 1998);

        //when
        // wyszukujemy film z dat?? produkcji kt??ra jest zdublowana
        List<Film> foundFilms = filmService.getFilmsByReleaseYear(releaseYear);

        //then
        // sprawdzimy czy dostali??my dwa filmy
        assertEquals(2, foundFilms.size());
        // sprawdzimy czy dane tych film??w s?? poprawne
        final Film film1 = foundFilms.stream()
                .filter(film -> film.getTitle().equals(SampleFilm.SAMPLE_FILM_TITLE))
                .findFirst()
                .orElse(null);
        final Film film2 = foundFilms.stream()
                .filter(film -> film.getTitle().equals("Git Ekipa"))
                .findFirst()
                .orElse(null);
        assertEquals(SampleFilm.defaultFilm(), film1);
        assertEquals(new Film("Git Ekipa", releaseYear), film2);
    }

    @Test
    void shouldAddFilmScore() {
        //given
        //dodajemy film
        final int score = 7;
        filmService.addFilm(SampleFilm.SAMPLE_FILM_TITLE, SampleFilm.SAMPLE_RELEASE_YEAR);

        //when
        //dodajemy ocen?? dla tego filmu
        filmService.addFilmScore(SampleFilm.SAMPLE_FILM_TITLE, SampleFilm.SAMPLE_RELEASE_YEAR, score);

        //then
        // sprawdzamy czy ocena zosta??a uwzgl??dniona
        final Optional<Film> film = filmService.getFilm(SampleFilm.SAMPLE_FILM_TITLE, SampleFilm.SAMPLE_RELEASE_YEAR);
        assertTrue(film.isPresent());
        assertNotNull(film.get().getScore());
        assertEquals(Integer.valueOf(score).doubleValue(), film.get().getScore().getValue());
        assertEquals(1, film.get().getScore().getCount());
    }

    @Test
    void shouldThrowExceptionWhenUserTryToFindTooOldFilm(){
        //given
        final int releaseYear = 1899;

        //when and then
        assertThrows(InvalidFilmException.class, () -> filmService.getFilmsByReleaseYear(releaseYear));
    }

    @Test
    void shouldThrowExceptionWhenUserTryToFindFilmWithFutureReleaseYear(){
        //given
        final int releaseYear = LocalDate.now().getYear() + 1;

        //when and then
        assertThrows(InvalidFilmException.class, () -> filmService.getFilmsByReleaseYear(releaseYear));
    }
}
