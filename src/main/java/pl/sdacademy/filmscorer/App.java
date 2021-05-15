package pl.sdacademy.filmscorer;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import pl.sdacademy.filmscorer.domain.FilmService;
import pl.sdacademy.filmscorer.domain.SimpleFilmScorer;
import pl.sdacademy.filmscorer.infrastructure.FilmEntity;
import pl.sdacademy.filmscorer.infrastructure.FilmJpaRepository;
import pl.sdacademy.filmscorer.ui.AddFilmCaseHandler;
import pl.sdacademy.filmscorer.ui.AddScoreCaseHandler;
import pl.sdacademy.filmscorer.ui.GetFilmCaseHandler;
import pl.sdacademy.filmscorer.ui.GetFilmsByReleaseYearCaseHandler;
import pl.sdacademy.filmscorer.ui.GetFilmsByTitleCaseHandler;
import pl.sdacademy.filmscorer.ui.UserInterface;

import java.util.Scanner;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        final FilmService filmService = new FilmService(new FilmJpaRepository(createSessionFactory()), new SimpleFilmScorer());
        final Scanner scanner = new Scanner(System.in);
        final UserInterface userInterface = new UserInterface(
                scanner,
                Set.of(
                        new GetFilmsByTitleCaseHandler(scanner, filmService),
                        new GetFilmCaseHandler(scanner, filmService),
                        new AddFilmCaseHandler(scanner, filmService),
                        new GetFilmsByReleaseYearCaseHandler(scanner, filmService),
                        new AddScoreCaseHandler(scanner, filmService)
                )
        );
        userInterface.start();
    }

    private static SessionFactory createSessionFactory() {
        Configuration configObj = new Configuration();
        configObj.configure("hibernate.cfg.xml");
        configObj.addAnnotatedClass(FilmEntity.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configObj.getProperties())
                .build();

        return configObj.buildSessionFactory(serviceRegistry);
    }
}
