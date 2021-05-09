package pl.sdacademy.filmscorer.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleFilmScorerTest {

    private SimpleFilmScorer simpleFilmScorer;

    @BeforeEach
    void setUp() {
        simpleFilmScorer = new SimpleFilmScorer();
    }

    @ParameterizedTest
    @CsvSource(value = {
         "10, 10, 20, 10",
         "7, 10, 10, 7.2",
         "10, 7, 10, 9.8"
    })
    void shouldCalculateCurrentScore(double actualScore, int inputScore, int scoresCount, double expectedResult){
        //when
        final Score newScore = simpleFilmScorer.calculate(new Score(actualScore, scoresCount), inputScore);

        //then
        assertEquals(expectedResult, newScore.getValue());
    }
}
