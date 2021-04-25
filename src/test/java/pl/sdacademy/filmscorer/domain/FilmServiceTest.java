package pl.sdacademy.filmscorer.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class FilmServiceTest {

    private FilmService filmService;
    private FilmRepository filmRepository;

    @BeforeEach
    void setup(){
        filmRepository = Mockito.mock(FilmRepository.class);
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
        Mockito.verify(filmRepository, Mockito.times(1)).saveOrThrowIfExists(new Film(title, releaseYear));
    }
}
