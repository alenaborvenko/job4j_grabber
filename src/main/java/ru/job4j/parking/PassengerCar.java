package ru.job4j.parking;

public class PassengerCar implements Transport {
    private int size = 1;

    @Override
    public int getSize() {
        return size;
    }
}
