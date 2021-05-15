package pl.sdacademy.filmscorer.infrastructure;

import pl.sdacademy.filmscorer.domain.Film;
import pl.sdacademy.filmscorer.domain.Score;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "films")
public class FilmEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "release_year")
    private int releaseYear;

    @Column(name = "scores_count")
    private long scoresCount;

    @Column(name = "actual_score")
    private double actualScore;

    public static FilmEntity fromDomain(Film film) {
        FilmEntity filmEntity = new FilmEntity();
        filmEntity.setId(film.getTitle() + film.getReleaseYear());
        filmEntity.setTitle(film.getTitle());
        filmEntity.setReleaseYear(film.getReleaseYear());

        if(film.getScore() != null) {
            filmEntity.setScoresCount(film.getScore().getCount());
            filmEntity.setActualScore(film.getScore().getValue());
        }

        return filmEntity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public long getScoresCount() {
        return scoresCount;
    }

    public void setScoresCount(long scoresCount) {
        this.scoresCount = scoresCount;
    }

    public double getActualScore() {
        return actualScore;
    }

    public void setActualScore(double actualScore) {
        this.actualScore = actualScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmEntity that = (FilmEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Film toFilm() {
        Film film = new Film(title, releaseYear);
        film.setScore(new Score(actualScore, Long.valueOf(scoresCount).intValue()));
        return film;
    }
}
