package ru.job4j.cache;

public class Emulator {
    public static void main(String[] args) {
        DirFileCache dirFileCache = new DirFileCache("src/main/resources/");
        String appProperties = dirFileCache.get("app.properties");
        System.out.println(appProperties);
        String rabbit = dirFileCache.get("rabbit.properties");
        System.out.println(rabbit);
    }
}
