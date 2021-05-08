package pl.sdacademy.filmscorer.ui;

import pl.sdacademy.filmscorer.domain.FilmService;

import java.util.Scanner;

public class GetFilmCaseHandler extends CaseHandler {

    public GetFilmCaseHandler(Scanner scanner, FilmService filmService) {
        super(2, "znajdź film", scanner, filmService);
    }

    @Override
    void handle() {
        System.out.println("Podaj tytuł");
        final String title = scanner.next();
        System.out.println("Podaj rok produkcji");
        final int releaseYear = scanner.nextInt();
        filmService.getFilm(title, releaseYear)
                .ifPresentOrElse(
                        film -> System.out.println("Znaleziono film " + film),
                        () -> System.out.println("Film o tytule " + title + " i roku wydania " + releaseYear + " nie znaleziony!")
                );
    }
}
