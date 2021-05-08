package pl.sdacademy.filmscorer.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.filmscorer.infrastructure.InMemoryFilmRepository;

import java.util.List;

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
}
