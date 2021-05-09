package pl.sdacademy.filmscorer.ui;

import pl.sdacademy.filmscorer.domain.FilmService;

import java.util.Scanner;

public class AddFilmCaseHandler extends CaseHandler{
    public AddFilmCaseHandler(Scanner scanner, FilmService filmService) {
        super(3, "dodaj film", scanner, filmService);
    }

    @Override
    void handle() {
        System.out.println("Podaj tytuł");
        final String title = scanner.next();
        System.out.println("Podaj rok produkcji");
        final int releaseYear = scanner.nextInt();
        try {
            filmService.addFilm(title, releaseYear);
            System.out.println("Film " + title + " z roku " + releaseYear + " dodany!");
        } catch (Exception e){
            System.out.println("Ups...coś poszło nie tak. " + e.getMessage());
        }
    }
}
