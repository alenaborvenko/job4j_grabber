package ru.job4j.isp.menu;

public class ConsoleOutput implements Output {
    @Override
    public void println(String text) {
        System.out.println(text);
    }
}
