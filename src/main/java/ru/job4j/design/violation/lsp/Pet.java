package ru.job4j.design.violation.lsp;

public class Pet {
    private int leg;

    public Pet(int leg) {
        this.leg = leg;
    }

    public int getLeg() {
        return leg;
    }

    public void run() {
        if (leg < 2) {
            throw new IllegalArgumentException("This animal cant run");
        }
        System.out.println("run!");
    }

    public void eat(int gramm) {
        if (gramm < 0) {
            throw new IllegalArgumentException("Error");
        }
        System.out.println("nyam nyam");
    }
}
