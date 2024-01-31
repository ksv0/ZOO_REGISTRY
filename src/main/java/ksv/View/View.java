package ksv.View;

import ksv.Model.Animal;

import java.util.List;
import java.util.Scanner;

import static ksv.View.Messages.*;

public class View {
    private Scanner scanner;

    public View() {
        this.scanner = new Scanner(System.in);
        //  Messages.setLocale(Language.ENGLISH.getLocale());
    }

    public void showLanguageMenu() {
        System.out.println(getMessage("CHOOSE_LANGUAGE"));
        System.out.println("1. English");
        System.out.println("2. Русский");
    }

    public void showLanguageSelected(String language) {
        System.out.println(getMessage("LANGUAGE_SELECTED", language));
    }

    public void showMenu() {
        System.out.println(getMessage("MENU_MESSAGES"));
    }

    public int getUserChoice() {
        System.out.print(getMessage("ENTER_CHOICE"));
        return scanner.nextInt();
    }

    public void showInvalidChoice() {
        System.out.println(getMessage("INVALID_CHOICE"));
    }

    public void showAllAnimals(List<Animal> animals) {
        System.out.println(getMessage("SHOW_ALL_ANIMALS"));
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }

    public void showMessage(String key, Object... args) {
        System.out.println(Messages.getMessage(key, args));
    }
    public String getInput(String prompt) {
        showMessage(prompt);
        return scanner.next();
    }
}

