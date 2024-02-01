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
        println(getMessage("CHOOSE_LANGUAGE"));
        println("1. English");
        println("2. Русский");
    }

    public void showLanguageSelected(String language) {
        println(getMessage("LANGUAGE_SELECTED", language));
    }

    public void showMenu() {
        println(getMessage("MENU_MESSAGES"));
    }

    public int getUserChoice() {
        boolean flag = true;
        int choice = 0;
        while (flag) {
            println(getMessage("ENTER_CHOICE"));
            if (scanner.hasNextInt()) {
                flag = false;
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();
            }

        }
        return choice;
    }


    public void showInvalidChoice() {
        println(getMessage("INVALID_CHOICE"));
    }

    public void showAllAnimals(List<Animal> animals) {
        println(getMessage("SHOW_ALL_ANIMALS"));
        for (Animal animal : animals) {
            println(animal.toString());
        }
    }

    public void showMessage(String key, Object... args) {
        println(Messages.getMessage(key, args));
    }

    public String getInput(String prompt) {
        showMessage(prompt);
        return scanner.nextLine();
    }

    public void println(String message) {
        try {
            PrintStream out = new PrintStream(System.out, true, "UTF-8");
            out.println(message);
        } catch (Exception e) {
            PrintStream out = new PrintStream(System.out, true);
            out.println(message);
        }
    }
}

