package pl.sdacademy.filmscorer.domain;

public class SimpleFilmScorer implements FilmScorer{
    @Override
    public Score calculate(Score currentScore, int scoreToAdd) {
        // a. film, który znaleźliśmy ma score == null i wtedy dodajemy nowy
        if (currentScore == null || currentScore.getCount() == 0) {
            return new Score(scoreToAdd, 1);
        }
        // b. film ma już score i wtedy chcemy zaktualizować istniejący
        double calculatedScore = currentScore.getValue() + (scoreToAdd - currentScore.getValue())/(currentScore.getCount() * 1.5);
        return new Score(calculatedScore, currentScore.getCount() + 1);
    }
}
