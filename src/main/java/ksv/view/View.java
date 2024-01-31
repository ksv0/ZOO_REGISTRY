package ksv.view;

import ksv.model.Animal;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static ksv.view.Messages.*;

public class View {
    private Scanner scanner;

    public View() {
        this.scanner = new Scanner(System.in);
    }

    public void showLanguageMenu() {
        coutln(getMessage("CHOOSE_LANGUAGE"));
        coutln("1. English");
        coutln("2. Русский");
    }

    public void showLanguageSelected(String language) {
        coutln(getMessage("LANGUAGE_SELECTED", language));
    }

    public void showMenu() {
        coutln(getMessage("MENU_MESSAGES"));
    }

    public int getUserChoice() {
        boolean flag = true;
        int choice = 0;
        while (flag) {
            coutln(getMessage("ENTER_CHOICE"));
            if (scanner.hasNextInt()) {
                flag = false;
                choice = scanner.nextInt();
            }else {
                scanner.nextLine();
            }

        }
        return choice;
    }


public void showInvalidChoice() {
    coutln(getMessage("INVALID_CHOICE"));
}

public void showAllAnimals(List<Animal> animals) {
    coutln(getMessage("SHOW_ALL_ANIMALS"));
    for (Animal animal : animals) {
        coutln(animal.toString());
    }
}

public void showMessage(String key, Object... args) {
    coutln(Messages.getMessage(key, args));
}

public String getInput(String prompt) {
    showMessage(prompt);
    return scanner.next();
}

private void coutln(String message) {
    try {
        PrintStream out = new PrintStream(System.out, true, "UTF-8");
        out.println(message);
    } catch (Exception e) {
    }
}
}

