package ru.job4j.isp.menu;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import static java.util.Collections.reverse;

public class Menu {
    private Item menuItem;
    private Output output;

    public Menu(Item menuItem, Output output) {
        this.menuItem = menuItem;
        this.output = output;
    }

    public boolean addParent(String name) {
        Item newItem = new MenuItem(name);
        newItem.setParent(menuItem);
        return menuItem.getChild().add(newItem);
    }

    public boolean addChild(String parentName, Item child) {
        Optional<Item> parent = findBy(parentName);
        if (parent.isEmpty()) {
            return false;
        }
        Item parentVal = parent.get();
        child.setParent(parentVal);
        parentVal.getChild().add(child);
        return true;
    }

    private Optional<Item> findBy(String name) {
        Optional<Item> rsl = Optional.empty();
        Queue<Item> data = new LinkedList<>();
        data.offer(this.menuItem);
        while (!data.isEmpty()) {
            Item el = data.poll();
            if (el.getName().equals(name)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.getChild());
        }
        return rsl;
    }

    public void print() {
        Deque<Item> data = new LinkedList<>();
        for (Item item : this.menuItem.getChild()) {
            data.offerFirst(item);
            while (!data.isEmpty()) {
                Item el = data.pollFirst();
                StringBuilder stringBuilder = new StringBuilder();
                Item tmp = el.getParent();
                while (tmp.getParent() != null) {
                    stringBuilder.append("----");
                    tmp = tmp.getParent();
                }
                output.println(stringBuilder.append(el.getName()).toString());
                reverse(el.getChild());
                el.getChild().forEach(data::addFirst);
            }
        }
    }
}
