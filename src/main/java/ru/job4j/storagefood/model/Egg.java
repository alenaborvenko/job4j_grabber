package ru.job4j.storagefood.model;

import java.time.LocalDate;

public class Egg extends Food {
    public Egg(String name, LocalDate expiryDate, LocalDate createDate,
               double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
