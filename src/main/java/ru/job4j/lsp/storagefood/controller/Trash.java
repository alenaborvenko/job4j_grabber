package ru.job4j.lsp.storagefood.controller;

import ru.job4j.lsp.storagefood.model.Food;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.lsp.storagefood.controller.Const.TRASH;

public class Trash implements Store {
    private List<Food> foods = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        return foods.add(food);
    }

    @Override
    public boolean execute(Food food) {
        return percent(food) >= TRASH;
    }

    @Override
    public List<Food> getFoodFromStore() {
        return foods;
    }
}
