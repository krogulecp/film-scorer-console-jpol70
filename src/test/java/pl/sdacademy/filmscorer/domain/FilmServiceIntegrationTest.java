package pl.sdacademy.filmscorer.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.filmscorer.infrastructure.InMemoryFilmRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilmServiceIntegrationTest {

    private FilmService filmService;
    private FilmRepository filmRepository;

    @BeforeEach
    void setUp() {
        filmRepository = new InMemoryFilmRepository();
        filmService = new FilmService(filmRepository);
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
}
