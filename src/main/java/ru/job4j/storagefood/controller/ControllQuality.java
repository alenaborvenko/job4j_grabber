package ru.job4j.storagefood.controller;

import ru.job4j.storagefood.model.Food;

import java.util.List;

public class ControllQuality {
    private List<Store> stores;

    public ControllQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void relocation(Food food) {
        for (Store currentStore : stores) {
            if (currentStore.execute(food)) {
                currentStore.add(food);
                break;
            }
        }
    }

    public void relocationList(List<Food> foods) {
        for (Food food : foods) {
            relocation(food);
        }
    }
}
