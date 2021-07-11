package ru.job4j.lsp.storagefood.controller;

import ru.job4j.lsp.storagefood.model.Food;

import java.util.ArrayList;
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

    public void resort() {
        List<Food> allFoodInStores = new ArrayList<>();
        for (Store currentStore : stores) {
            allFoodInStores.addAll(currentStore.getFoodFromStore());
            currentStore.getFoodFromStore().clear();
        }
        relocationList(allFoodInStores);
    }
}
