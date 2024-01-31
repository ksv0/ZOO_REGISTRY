package ksv.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Registry implements Serializable  {
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
    public boolean saveZooToFile(String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(animals);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean loadZooFromFile(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            animals = (List<Animal>) inputStream.readObject();
            return true;
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
    }
    public List<Animal> getAllAnimals() {
        return new ArrayList<>(animals);
    }
}
