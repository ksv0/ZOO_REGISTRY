package ksv.model.animals;
import ksv.model.Animal;
import ksv.view.Messages;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;

public class PackAnimal extends Animal implements Serializable {
    private String kind;

    public PackAnimal(String name, String birthDate, List<String> commands, String kind) {
        super(name, birthDate, commands);
        this.kind = kind;
    }

    @Override
    public String toString() {
        return super.toString() + " " + MessageFormat.format(Messages.getMessage("TO_STRING_PACK_ANIMAL"), kind);
    }
}
