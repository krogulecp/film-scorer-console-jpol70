package pl.sdacademy.filmscorer.domain;

import org.junit.jupiter.api.BeforeEach;

class FilmServiceTest {

    private FilmService filmService;

    @BeforeEach
    void setup(){
        filmService = new FilmService(filmRepository);
    }

    void shouldAddFilm() {
        //given
        final String title = "Rambo";
        final int releaseYear = 1980;

        //when
        filmService.addFilm(title, releaseYear);

        //then
    }
}
