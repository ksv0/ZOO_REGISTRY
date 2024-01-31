package ksv.Model;

import java.util.Arrays;

public class Animal {
    private String name;
    private String birthDate;
    private String[] commands;
    private boolean isDomestic;

    public Animal(String name, String birthDate, String[] commands, boolean isDomestic) {
        this.name = name;
        this.birthDate = birthDate;
        this.commands = commands;
        this.isDomestic = isDomestic;
    }



    @Override
    public String toString() {
        String domesticStatus = isDomestic ? "Yes" : "No";
        String commandsList = String.join(", ", commands);

        return String.format("Name: %s, Birth Date: %s, Commands: %s, Domestic: %s",
                name, birthDate, commandsList, domesticStatus);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String[] getCommands() {
        return commands;
    }

    public void setCommands(String[] commands) {
        this.commands = commands;
    }

    public boolean isDomestic() {
        return isDomestic;
    }

    public void setDomestic(boolean domestic) {
        isDomestic = domestic;
    }
}