package pl.sdacademy.filmscorer.ui;

import pl.sdacademy.filmscorer.domain.FilmService;

import java.util.Scanner;

public abstract class CaseHandler {
    private final int id;
    private final String title;
    protected final Scanner scanner;
    protected final FilmService filmService;

    protected CaseHandler(int id, String title, Scanner scanner, FilmService filmService) {
        this.id = id;
        this.title = title;
        this.scanner = scanner;
        this.filmService = filmService;
    }

    abstract void handle();

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
