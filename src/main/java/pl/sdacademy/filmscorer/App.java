package pl.sdacademy.filmscorer;

import pl.sdacademy.filmscorer.domain.FilmService;
import pl.sdacademy.filmscorer.infrastructure.InMemoryFilmRepository;
import pl.sdacademy.filmscorer.ui.UserInterface;

public class App {
    public static void main(String[] args) {
        final FilmService filmService = new FilmService(new InMemoryFilmRepository());
        final UserInterface userInterface = new UserInterface(filmService);
        userInterface.start();
    }
}
