package pl.sdacademy.filmscorer.ui;

import pl.sdacademy.filmscorer.domain.Film;
import pl.sdacademy.filmscorer.domain.FilmService;

import java.util.List;
import java.util.Scanner;

public class GetFilmsByTitleCaseHandler extends CaseHandler {

    public GetFilmsByTitleCaseHandler(Scanner scanner, FilmService filmService) {
        super(1, "znajdź filmy po tytule", scanner, filmService);
    }

    @Override
    void handle() {
        System.out.println("Podaj tytuł");
        final String title = scanner.nextLine();
        final List<Film> foundFilms = filmService.getFilmsByTitle(title);
        System.out.println("Found films " + foundFilms);
    }
}
