package ru.job4j.isp.menu;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MenuTest {
    private StringBuilder stringBuilder = new StringBuilder();
    private Output output = text -> stringBuilder.append(text).append(System.lineSeparator());

    @Test
    public void testMenu() {
        Menu menu = new Menu(new MenuItem("root"), output);
        menu.addParent("Задача 1");
        menu.addParent("Задача 2");
        menu.addChild("Задача 1", new MenuItem("Задача 1.1"));
        menu.addChild("Задача 1", new MenuItem("Задача 1.2"));
        menu.addChild("Задача 1.2", new MenuItem("Задача 1.2.1"));
        menu.addChild("Задача 1", new MenuItem("Задача 1.3"));
        menu.addChild("Задача 1.3", new MenuItem("Задача 1.3.1"));
        menu.addChild("Задача 1.3", new MenuItem("Задача 1.3.2"));
        menu.addChild("Задача 2", new MenuItem("Задача 2.1"));
        menu.addChild("Задача 2", new MenuItem("Задача 2.2"));
        menu.print();
        System.out.println(stringBuilder);
        assertThat(stringBuilder.toString(), is("Задача 1" + System.lineSeparator()
                + "----Задача 1.1" + System.lineSeparator()
                + "----Задача 1.2" + System.lineSeparator()
                + "--------Задача 1.2.1" + System.lineSeparator()
                + "----Задача 1.3" + System.lineSeparator()
                + "--------Задача 1.3.1" + System.lineSeparator()
                + "--------Задача 1.3.2" + System.lineSeparator()
                + "Задача 2" + System.lineSeparator()
                + "----Задача 2.1" + System.lineSeparator()
                + "----Задача 2.2" + System.lineSeparator()));
    }
}