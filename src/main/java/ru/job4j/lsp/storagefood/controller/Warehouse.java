package ru.job4j.lsp.storagefood.controller;

import ru.job4j.lsp.storagefood.model.Food;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.lsp.storagefood.controller.Const.MIN_PERCENT;

public class Warehouse implements Store {
    private List<Food> foods = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        return foods.add(food);
    }

    @Override
    public boolean execute(Food food) {
        return percent(food) < MIN_PERCENT;
    }

    @Override
    public List<Food> getFoodFromStore() {
        return foods;
    }
}
