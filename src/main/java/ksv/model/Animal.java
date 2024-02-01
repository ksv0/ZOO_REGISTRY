package ksv.model;

import ksv.view.Messages;

import java.io.Serializable;
import java.text.MessageFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.List;

public abstract class Animal implements Serializable {
    protected String name;
    protected LocalDate birthDate;
    private List<String> commands;

    protected Animal(String name, String birthDate, List<String> commands) {
        this.name = name;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            this.birthDate = LocalDate.parse(birthDate,formatter);
        } catch (DateTimeParseException e) {
            this.birthDate = LocalDate.now();
        }
        this.commands = commands;
    }



    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return MessageFormat.format(Messages.getMessage("TO_STRING_ANIMAL_WITH_COMMANDS"),
                name,formatter.format(birthDate), calculateAge(), commands);
    }

    protected int calculateAge() {

        return 0;
    }

    public void setCommands(List<String> newCommands) {
        commands = newCommands;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<String> getCommands() {
        return commands;
    }
}