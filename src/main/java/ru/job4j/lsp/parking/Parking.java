package ru.job4j.lsp.parking;

public interface Parking {
    void add(Transport transport);

    void delete(Transport transport);

    int getPassengerCarSize();

    int getTruckCarSize();
}
