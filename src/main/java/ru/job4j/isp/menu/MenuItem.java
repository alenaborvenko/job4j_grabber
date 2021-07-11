package ru.job4j.isp.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuItem implements Action, Item {
    private String name;
    private List<Item> child;
    private Item parent;

    public MenuItem(String name) {
        this.name = name;
        this.child = new ArrayList<>();
    }

    public Item getParent() {
        return parent;
    }

    @Override
    public void setParent(Item parent) {
        this.parent = parent;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Item> getChild() {
        return child;
    }

    @Override
    public void action() {

    }
}
