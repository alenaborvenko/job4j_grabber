package ru.job4j.parking;

public interface Parking {
    int getPlace();

    void takeFreePlace(Transport transport);

    void add(Transport transport);

    void delete(Transport transport);
}
