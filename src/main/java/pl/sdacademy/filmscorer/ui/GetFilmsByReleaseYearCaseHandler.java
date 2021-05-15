package pl.sdacademy.filmscorer.ui;

import pl.sdacademy.filmscorer.domain.Film;
import pl.sdacademy.filmscorer.domain.FilmService;

import java.util.List;
import java.util.Scanner;

public class GetFilmsByReleaseYearCaseHandler extends CaseHandler{
    public GetFilmsByReleaseYearCaseHandler(Scanner scanner, FilmService filmService) {
        super(4, "znajdź filmy po roku produkcji", scanner, filmService);
    }

    @Override
    void handle() {
        System.out.println("Podaj rok produkcji");
        final int releaseYear = scanner.nextInt();
        final List<Film> foundFilms = filmService.getFilmsByReleaseYear(releaseYear);
        if (foundFilms.isEmpty()){
            System.out.println("Nie znaleziono żadnego filmu!");
        } else {
            System.out.println("Znaleziono filmy: ");
            foundFilms.forEach(System.out::println);
        }
    }
}
