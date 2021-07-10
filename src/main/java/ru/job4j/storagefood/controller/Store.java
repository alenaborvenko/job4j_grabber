package ru.job4j.storagefood.controller;

import ru.job4j.storagefood.model.Food;

import java.time.LocalDate;
import java.util.List;

public interface Store {

    boolean add(Food food);

    boolean execute(Food food);

    default double percent(Food food) {
        if (food.getNow() == null) {
            food.setNow(LocalDate.now());
        }
        return (double) (food.getNow().toEpochDay() - food.getCreateDate().toEpochDay())
                / (food.getExpiryDate().toEpochDay() - food.getCreateDate().toEpochDay());
    }

    List<Food> getFoodFromStore();
}
