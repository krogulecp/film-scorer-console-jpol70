package pl.sdacademy.filmscorer.ui;

import pl.sdacademy.filmscorer.domain.FilmService;

import java.util.Scanner;

public class AddScoreCaseHandler extends CaseHandler{
    public AddScoreCaseHandler(Scanner scanner, FilmService filmService) {
        super(5, "dodaj ocenę", scanner, filmService);
    }

    @Override
    void handle() {
        System.out.println("Podaj tytuł filmu");
        final String title = scanner.next();
        System.out.println("Podaj rok produkcji");
        final int releaseYear = scanner.nextInt();
        System.out.println("Podaj ocenę od 1 do 10");
        final int score = scanner.nextInt();
        try {
            filmService.addFilmScore(title, releaseYear, score);
        } catch (Exception exception) {
            System.out.println("Ups... coś poszło nie tak: " + exception.getMessage());
        }
    }
}
