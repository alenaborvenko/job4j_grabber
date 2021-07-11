package ru.job4j.parking;

public class TruckCar implements Transport {
    private int size;

    public TruckCar(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
