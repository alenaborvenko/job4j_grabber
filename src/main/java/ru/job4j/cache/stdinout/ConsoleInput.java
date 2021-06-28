package ru.job4j.cache.stdinout;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private Scanner input = new Scanner(System.in);
    private Output output;

    public ConsoleInput(Output output) {
        this.output = output;
    }

    @Override
    public String askStr(String question) {
        output.println(question);
        return input.nextLine();
    }

    @Override
    public int askInt(String question) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = Integer.parseInt(this.askStr(question));
                invalid = false;
            } catch (NumberFormatException nfe) {
                output.println("Please enter validate data again.");
            }
        } while (invalid);
        return value;
    }
}
