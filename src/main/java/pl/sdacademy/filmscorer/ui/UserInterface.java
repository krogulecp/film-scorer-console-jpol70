package pl.sdacademy.filmscorer.ui;

import java.util.Comparator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class UserInterface {
    private final Scanner input;
    private final Set<CaseHandler> caseHandlers;

    public UserInterface(Scanner input, Set<CaseHandler> caseHandlers) {
        this.input = input;
        this.caseHandlers = caseHandlers;
    }

    public void start() {
        boolean shouldContinue = true;
        System.out.println("Witam w aplikacji do oceny filmów!");
        while (shouldContinue) {
            System.out.println("Co chciałbyś zrobić:");
            caseHandlers.stream()
                    .sorted(Comparator.comparingInt(CaseHandler::getId))
                    .forEach(handler -> System.out.println(handler.getId() + " -> " + handler.getTitle()));
            final String selectedOption = input.next();
            try {
                final int selectedOptionNumber = Integer.valueOf(selectedOption);
                this.caseHandlers.stream()
                        .filter(handler -> handler.getId() == selectedOptionNumber).findFirst()
                        .ifPresentOrElse(CaseHandler::handle, () -> System.out.println("Wybrano błędną opcję"));
            } catch (Exception exception){
                System.out.println("Błąd podczas wybierania opcji. " + exception.getMessage());
            }

            shouldContinue = shouldContinue();
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
