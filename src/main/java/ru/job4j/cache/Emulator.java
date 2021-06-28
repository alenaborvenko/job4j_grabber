package ru.job4j.cache;

import ru.job4j.cache.stdinout.ConsoleInput;
import ru.job4j.cache.stdinout.ConsoleOutput;
import ru.job4j.cache.stdinout.Input;
import ru.job4j.cache.stdinout.Output;

import java.io.IOException;

public class Emulator {
    private static DirFileCache dirFileCache;
    private static String cache;

    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ConsoleInput(output);
        while (true) {
            initMenu(output);
            int select = input.askInt("Select:");
            if (select == 1) {
                String path = input.askStr("Enter path:");
                dirFileCache = new DirFileCache(path);
            } else if (select == 2) {
                String nameFile = input.askStr("Enter file name from path:");
                try {
                    cache = dirFileCache.get(nameFile);
                    output.println(cache);
                } catch (IOException e) {
                    output.println("File not found from directory");
                } catch (NullPointerException ex) {
                    output.println("First select path");
                }
            } else if (select == 3) {
                dirFileCache = null;
            } else {
                System.exit(0);
            }
        }
    }

    private static void initMenu(Output output) {
        output.println("Menu:");
        output.println("1. Enter path");
        output.println("2. Get cache by key");
        output.println("3. Clean cache");
        output.println("4. Exit");
    }
}
