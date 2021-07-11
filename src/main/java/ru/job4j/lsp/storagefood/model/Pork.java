package ru.job4j.lsp.storagefood.model;

import java.time.LocalDate;

public class Pork extends Food {
    public Pork(String name, LocalDate expiryDate, LocalDate createDate, double price,
                double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
