package pl.sdacademy.filmscorer.domain;

import java.util.Objects;

public class Film {
    private final String title;
    private final int releaseYear;
    private Score score;

    public Film(String title, int releaseYear) {
        this.title = title;
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public Score getScore(){
        return score;
    }

    public void setScore(Score score){
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return releaseYear == film.releaseYear &&
                title.equals(film.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, releaseYear);
    }

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", releaseYear=" + releaseYear +
                ", score=" + score +
                '}';
    }
}

