package ksv.Presenter;

import ksv.Model.Animal;
import ksv.Model.Registry;
import ksv.View.View;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Presenter {
    private View view;
    private Registry registry;

    public Presenter(View view, Registry registry) {
        this.view = view;
        this.registry = registry;
    }

    public void start()  {
        int choice;


        while (true) {
            view.showMenu();
            choice = view.getUserChoice();

            switch (choice) {
                case 1:
                    processAddAnimal();
                    break;
                case 2:
                    processRemoveAnimal();
                    break;
                case 3:
                    processShowAnimalDetails();
                    break;
                case 4:
                    processTrainAnimal();
                    break;
                case 5:
                    processShowAllAnimals();
                    break;
                case 6:
                    return;
                default:
                    view.showInvalidChoice();
            }

        }
    }

    private void processAddAnimal() {
        // Логика добавления нового животного в реестр
        String name = view.getInput("ADD_ANIMAL_PROMPT");
        String birthDate = view.getInput("ADD_BIRTH_DATE_PROMPT");
        boolean isDomestic = Boolean.parseBoolean(view.getInput("ADD_DOMESTIC_PROMPT"));
        String[] commands = processCommandInput();
        registry.addAnimal(new Animal(name, birthDate, commands, isDomestic));
        view.showMessage("ANIMAL_ADDED");
    }

    private void processRemoveAnimal() {
        // Логика удаления животного из реестра
        String name = view.getInput("REMOVE_ANIMAL_PROMPT");
        registry.removeAnimal(name);
        view.showMessage("ANIMAL_REMOVED");
    }

    private void processShowAnimalDetails() {
        // Логика отображения деталей о животном
        String name = view.getInput("ADD_ANIMAL_PROMPT");
        Animal animal = registry.findAnimal(name);
        if (animal != null) {
            view.showMessage(animal.toString());
        } else {
            view.showMessage("ANIMAL_NOT_FOUND", name);
        }
    }

    private void processTrainAnimal() {
        // Логика обучения животного
        String name = view.getInput("ADD_ANIMAL_PROMPT");
        Animal animal = registry.findAnimal(name);
        if (animal != null) {
            String[] newCommands = processCommandInput();
            animal.setCommands(newCommands);
            view.showMessage("TRAINING_SUCCESSFUL", name, String.join(", ", newCommands));
        } else {
            view.showMessage("ANIMAL_NOT_FOUND", name);
        }
    }

    private void processShowAllAnimals() {
        List<Animal> animals = registry.getAllAnimals();
        view.showAllAnimals(animals);
    }


    private String[] processCommandInput() {
        // Логика ввода команд
        // Введите команды (введите 'done', чтобы закончить):
        List<String> commands = new ArrayList<>();
        String input;
        while (!(input = view.getInput("NEW_COMMANDS_PROMPT")).equalsIgnoreCase("done")) {
            commands.add(input);
        }
        return commands.toArray(new String[0]);
    }
}