package pl.sdacademy.filmscorer.domain;

class Film {
    private final String title;
    private final int releaseYear;

    Film(String title, int releaseYear) {
        this.title = title;
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }
}

