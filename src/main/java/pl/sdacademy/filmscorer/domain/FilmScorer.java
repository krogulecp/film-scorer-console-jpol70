package pl.sdacademy.filmscorer.domain;

public interface FilmScorer {
    Score calculate(Score currentScore, int scoreToAdd);
}
