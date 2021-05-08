package pl.sdacademy.filmscorer.domain;

public class SampleFilm {
    public static final String SAMPLE_FILM_TITLE = "Rambo";
    public static final int SAMPLE_RELEASE_YEAR = 1980;

    static Film defaultFilm(){
        return new Film(SAMPLE_FILM_TITLE, SAMPLE_RELEASE_YEAR);
    }
}
