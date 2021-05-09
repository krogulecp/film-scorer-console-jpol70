package pl.sdacademy.filmscorer;

import pl.sdacademy.filmscorer.domain.FilmService;
import pl.sdacademy.filmscorer.infrastructure.InMemoryFilmRepository;
import pl.sdacademy.filmscorer.ui.AddFilmCaseHandler;
import pl.sdacademy.filmscorer.ui.GetFilmCaseHandler;
import pl.sdacademy.filmscorer.ui.GetFilmsByReleaseYearCaseHandler;
import pl.sdacademy.filmscorer.ui.GetFilmsByTitleCaseHandler;
import pl.sdacademy.filmscorer.ui.UserInterface;

import java.util.Scanner;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        final FilmService filmService = new FilmService(new InMemoryFilmRepository());
        final Scanner scanner = new Scanner(System.in);
        final UserInterface userInterface = new UserInterface(
                scanner,
                Set.of(
                        new GetFilmsByTitleCaseHandler(scanner, filmService),
                        new GetFilmCaseHandler(scanner, filmService),
                        new AddFilmCaseHandler(scanner, filmService),
                        new GetFilmsByReleaseYearCaseHandler(scanner, filmService)
                )
        );
        userInterface.start();
    }
}
