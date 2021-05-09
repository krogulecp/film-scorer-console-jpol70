package pl.sdacademy.filmscorer.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.sdacademy.filmscorer.infrastructure.DuplicatedKeyException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FilmServiceTest {

    private FilmService filmService;
    private FilmRepository filmRepository;
    private FilmScorer filmScorer;

    @BeforeEach
    void setup(){
        filmRepository = Mockito.mock(FilmRepository.class);
        filmScorer = Mockito.mock(FilmScorer.class);
        filmService = new FilmService(filmRepository, filmScorer);
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

    @Test
    void should_throw_duplicatedFilmException_when_adding_same_film_twice(){
        //given
        final String title = "Rambo";
        final int releaseYear = 1980;

        //when
        Mockito.doThrow(DuplicatedKeyException.class).when(filmRepository).saveOrThrowIfExists(new Film(title, releaseYear));

        //then
        assertThrows(DuplicatedFilmException.class, () -> filmService.addFilm(title, releaseYear));
    }
}
