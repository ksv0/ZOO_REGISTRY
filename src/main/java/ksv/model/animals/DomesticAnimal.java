package ksv.model.animals;
import ksv.model.Animal;
import ksv.view.Messages;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;

public class DomesticAnimal extends Animal implements Serializable {
    private String kind;

    public DomesticAnimal(String name, String birthDate, List<String> commands, String kind) {
        super(name, birthDate, commands);
        this.kind = kind;
    }

    @Override
    public String toString() {
        return MessageFormat.format(Messages.getMessage("TO_STRING_DOMESTIC_ANIMAL"), kind)+super.toString();
    }
}
