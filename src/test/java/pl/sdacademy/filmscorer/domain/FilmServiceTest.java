package pl.sdacademy.filmscorer.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FilmServiceTest {

    private FilmService filmService;
    private FilmRepository filmRepository;

    @BeforeEach
    void setup(){
        filmService = new FilmService(filmRepository);
    }

    @Test
    void shouldAddFilm() {
        //given
        final String title = "Rambo";
        final int releaseYear = 1980;

        //when
        filmService.addFilm(title, releaseYear);

        //then
    }
}
