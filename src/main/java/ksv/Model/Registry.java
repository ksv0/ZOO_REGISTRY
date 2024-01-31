package ksv.Model;

import java.util.ArrayList;
import java.util.List;

public class Registry {
    private List<Animal> animals;

    public Registry() {
        this.animals = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(String name) {
        animals.removeIf(animal -> animal.getName().equals(name));
    }

    public Animal findAnimal(String name) {
        return animals.stream()
                .filter(animal -> animal.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<Animal> getAllAnimals() {
        return new ArrayList<>(animals);
    }
}
