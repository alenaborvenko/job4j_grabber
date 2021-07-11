package ru.job4j.isp.menu;

import java.util.List;

public interface Item {
    List<Item> getChild();

    String getName();

    void setName(String name);

    Item getParent();

    void setParent(Item parent);
}
