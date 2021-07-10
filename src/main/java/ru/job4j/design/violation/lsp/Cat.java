package ru.job4j.design.violation.lsp;

public class Cat extends Pet {
    public Cat(int leg) {
        super(leg);
    }

    // нарушение предусловия
    @Override
    public void run() {
        if (this.getLeg() < 4) {
            throw new IllegalArgumentException("cat cant run :(");
        }
        System.out.println("run cat!");
    }

    //нарушение постусловия
    @Override
    public void eat(int gramm) {
        System.out.println("nyam");
    }
}
