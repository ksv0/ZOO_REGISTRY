package ksv.presenter;

import ksv.model.Animal;
import ksv.model.Registry;
import ksv.model.animals.DomesticAnimal;
import ksv.model.animals.PackAnimal;
import ksv.view.Messages;
import ksv.view.View;

import java.util.ArrayList;
import java.util.List;

public class Presenter {
    private View view;
    private Registry registry;

    public Presenter(View view, Registry registry) {
        this.view = view;
        this.registry = registry;
    }

    public void start() {
        int choice;
        boolean flag = registry.loadZooFromFile(Messages.getMessage(ZOO_FILENAME));
        if (flag) {
            view.showMessage(Messages.getMessage("ZOO_LOADED"),Messages.getMessage(ZOO_FILENAME));
        } else {
            view.showMessage(Messages.getMessage("ZOO_NOT_LOADED"),Messages.getMessage(ZOO_FILENAME));
        }



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
                    flag = registry.saveZooToFile(Messages.getMessage(ZOO_FILENAME));
                    if (flag) {
                        view.showMessage(Messages.getMessage("ZOO_SAVED"),Messages.getMessage(ZOO_FILENAME));
                    } else {
                        view.showMessage(Messages.getMessage("ZOO_NOT_SAVED"),Messages.getMessage(ZOO_FILENAME));
                    }
                    return;
                default:
                    view.showInvalidChoice();
            }

        }
    }

    private void processAddAnimal() {
        String name = view.getInput(ADD_ANIMAL_PROMPT);

        String birthDate = view.getInput("ADD_BIRTH_DATE_PROMPT");
        boolean isDomestic = Boolean.parseBoolean(view.getInput("ADD_DOMESTIC_PROMPT"));
        List<String> commands = processCommandInput();
        String kind = view.getInput("ADD_KIND_PROMPT");
        registry.addAnimal(isDomestic ? new DomesticAnimal(name, birthDate, commands, kind) : new PackAnimal(name, birthDate, commands, kind));
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
        String name = view.getInput(ADD_ANIMAL_PROMPT);
        Animal animal = registry.findAnimal(name);
        if (animal != null) {
            view.showMessage(animal.toString());
        } else {
            view.showMessage("ANIMAL_NOT_FOUND", name);
        }
    }

    private void processTrainAnimal() {
        // Логика обучения животного
        String name = view.getInput(ADD_ANIMAL_PROMPT);
        Animal animal = registry.findAnimal(name);
        if (animal != null) {
            List<String> newCommands = processCommandInput();
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


    private List<String> processCommandInput() {
        List<String> commands = new ArrayList<>();
        String input;
        while (!(input = view.getInput("NEW_COMMANDS_PROMPT")).equalsIgnoreCase("done")) {
            commands.add(input);
        }
        return commands;
    }

    private static final String ADD_ANIMAL_PROMPT = "ADD_ANIMAL_PROMPT";
    private static final String ZOO_FILENAME = "ZOO_FILENAME";
}