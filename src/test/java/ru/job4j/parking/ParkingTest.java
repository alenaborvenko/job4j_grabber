package ru.job4j.parking;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ParkingTest {
    @Test
    public void parkingTest() {
        Transport auto = new PassengerCar();
        Transport truck = new TruckCar(10);
        Parking parking = new ParkingTransport(11);
        parking.add(auto);
        parking.add(truck);
        assertThat(parking.getPlace(), is(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void parkingCantAddCarTest() {
        Transport auto = new PassengerCar();
        Transport truck = new TruckCar(10);
        Parking parking = new ParkingTransport(11);
        parking.add(auto);
        parking.add(truck);
        parking.add(truck);
    }

    @Test
    public void parkingAddCarTest() {
        Transport auto = new PassengerCar();
        Transport truck = new TruckCar(10);
        Parking parking = new ParkingTransport(11);
        parking.add(auto);
        parking.add(truck);
        parking.delete(truck);
        parking.add(truck);
    }
}