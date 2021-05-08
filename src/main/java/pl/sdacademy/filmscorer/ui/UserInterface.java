package pl.sdacademy.filmscorer.ui;

import pl.sdacademy.filmscorer.domain.FilmService;

import java.util.Scanner;

public class UserInterface {
    private final FilmService filmService;
    private final Scanner input;

    public UserInterface(FilmService filmService, Scanner input) {
        this.filmService = filmService;
        this.input = input;
    }

    public void start() {
        boolean shouldContinue = true;
        System.out.println("Witam w aplikacji do oceny filmów!");
        while (shouldContinue) {
            System.out.println("Co chciałbyś zrobić:");
            System.out.println("1 -> dodać film do bazy");
            final int selectedOption = input.nextInt();
            switch (selectedOption){
                case 1:
                    addFilmCase();
                    break;
            }
            shouldContinue = shouldContinue();
        }
    }

    private void addFilmCase() {
        System.out.println("Podaj tytuł");
        final String title = input.next();
        System.out.println("Podaj rok produkcji");
        final int releaseYear = input.nextInt();
        try {
            filmService.addFilm(title, releaseYear);
            System.out.println("Film " + title + " z roku " + releaseYear + " dodany!");
        } catch (Exception e){
            System.out.println("Ups...coś poszło nie tak. " + e.getMessage());
        }
    }

    private boolean shouldContinue(){
        System.out.println("Czy chcesz kontynuować? (t -> tak, pozostałe klawisze -> nie)");
        final String selectedOption = input.next();
        switch (selectedOption) {
            case "t":
                return true;
            default:
                return false;
        }
    }


}