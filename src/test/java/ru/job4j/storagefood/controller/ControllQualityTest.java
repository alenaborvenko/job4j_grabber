package ru.job4j.storagefood.controller;

import org.junit.Test;
import ru.job4j.storagefood.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ControllQualityTest {
    @Test
    public void testRelocation() {
        Food egg = new Egg("egg", LocalDate.of(2021, 7, 26),
                LocalDate.of(2021, 7, 1), 90, 0);
        Food beef = new Beef("beef", LocalDate.of(2021, 7, 16),
                LocalDate.of(2021, 7, 11), 343, 0);
        Food milk = new Milk("milk", LocalDate.of(2021, 7, 10),
                LocalDate.of(2021, 7, 1), 80, 0);
        Food pork = new Pork("pork", LocalDate.of(2021, 7, 12),
                LocalDate.of(2021, 7, 4), 265, 0);
        LocalDate now = LocalDate.of(2021, 7, 11);
        egg.setNow(now);
        beef.setNow(now);
        milk.setNow(now);
        pork.setNow(now);
        List<Food> foods = List.of(egg, beef, milk, pork);
        Store shope = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        ControllQuality controllQuality = new ControllQuality(List.of(shope, warehouse, trash));
        controllQuality.relocationList(foods);
        assertThat(shope.getFoodFromStore(), is(new ArrayList<>(List.of(egg, pork))));
        assertThat(pork.getDiscount(), is(0.5));
        assertThat(warehouse.getFoodFromStore().get(0), is(beef));
        assertThat(trash.getFoodFromStore().get(0), is(milk));
    }
}