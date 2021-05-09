package pl.sdacademy.filmscorer.ui;

import pl.sdacademy.filmscorer.domain.FilmService;

import java.util.Scanner;

public class GetFilmsByReleaseYearCaseHandler extends CaseHandler{
    protected GetFilmsByReleaseYearCaseHandler(Scanner scanner, FilmService filmService) {
        super(4, "znajdź filmy po roku produkcji", scanner, filmService);
    }

    @Override
    void handle() {
        //TODO
    }
}
