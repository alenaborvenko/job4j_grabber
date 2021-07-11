package ru.job4j.lsp.storagefood.controller;

import ru.job4j.lsp.storagefood.model.Food;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.lsp.storagefood.controller.Const.*;

public class Shop implements Store {
    private List<Food> foods = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        return foods.add(food);
    }

    @Override
    public boolean execute(Food food) {
        double percent = percent(food);
        if (percent >= MAX_PERCENT && percent < TRASH) {
            food.setDiscount(0.5);
        }
        return percent >= MIN_PERCENT && percent < TRASH;
    }

    @Override
    public List<Food> getFoodFromStore() {
        return foods;
    }
}
