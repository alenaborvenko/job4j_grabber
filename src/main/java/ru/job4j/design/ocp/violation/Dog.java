package ru.job4j.design.ocp.violation;

import java.util.ArrayList;
import java.util.List;

public class Dog {
    private String name;
    private String owner;
    private int age;
    //использование конректной реализации в типе вместо интерфейса или абстрактного класса
    private ArrayList<Dog> dogs = new ArrayList<>();

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public void add(Dog dog) {
        dogs.add(dog);
    }

    //возвращаемый тип метода конкретная реализация, а не абстракция
    public ArrayList<Dog> findDogsByName(String name) {
        ArrayList<Dog> rsl = new ArrayList<>();
        for (Dog dog : dogs) {
            if (name.equals(dog.getName())) {
                rsl.add(dog);
            }
        }
        return rsl;
    }

    //нужно использовать метод поиск по предикату
    public List<Dog> findDogsByOwner(String name) {
        List<Dog> rsl = new ArrayList<>();
        for (Dog dog : dogs) {
            if (name.equals(dog.getName())) {
                rsl.add(dog);
            }
        }
        return rsl;
    }
}
