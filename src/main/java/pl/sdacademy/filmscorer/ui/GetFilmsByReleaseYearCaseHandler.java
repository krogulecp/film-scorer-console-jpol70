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
        System.out.println("Znaleziono filmy: ");
        foundFilms.forEach(System.out::println);
        //TODO W przypadku nie znalezienia żadnego filmu wypisać komunikat: "Nie znaleziono żadnego filmu"
    }
}
