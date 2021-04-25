package pl.sdacademy.filmscorer;

import pl.sdacademy.filmscorer.domain.FilmService;
import pl.sdacademy.filmscorer.infrastructure.InMemoryFilmRepository;
import pl.sdacademy.filmscorer.ui.UserInterface;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        final FilmService filmService = new FilmService(new InMemoryFilmRepository());
        final UserInterface userInterface = new UserInterface(filmService, new Scanner(System.in));
        userInterface.start();
    }
}
