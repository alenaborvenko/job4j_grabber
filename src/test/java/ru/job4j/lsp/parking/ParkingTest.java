package ru.job4j.lsp.parking;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ParkingTest {
    @Test
    public void parkingTest() {
        Transport auto = new PassengerCar();
        Transport truck = new TruckCar(10);
        Parking parking = new ParkingTransport(1, 1);
        parking.add(auto);
        parking.add(truck);
        assertThat(parking.getPassengerCarSize(), is(0));
        assertThat(parking.getTruckCarSize(), is(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void parkingCantAddCarTest() {
        Transport auto = new PassengerCar();
        Transport truck = new TruckCar(10);
        Parking parking = new ParkingTransport(1, 1);
        parking.add(auto);
        parking.add(truck);
        parking.add(truck);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parkingCantAddPassengerCarTest() {
        Transport auto = new PassengerCar();
        Parking parking = new ParkingTransport(0, 1);
        parking.add(auto);
    }

    @Test
    public void parkingAddCarTest() {
        Transport auto = new PassengerCar();
        Transport truck = new TruckCar(10);
        Parking parking = new ParkingTransport(1, 1);
        parking.add(auto);
        parking.add(truck);
        parking.delete(truck);
        parking.add(truck);
        assertThat(parking.getPassengerCarSize(), is(0));
        assertThat(parking.getTruckCarSize(), is(0));
    }

    @Test
    public void parkingDeleteTruck() {
        Transport auto = new PassengerCar();
        Transport truck = new TruckCar(10);
        Parking parking = new ParkingTransport(1, 1);
        parking.add(auto);
        parking.add(truck);
        parking.delete(truck);
        assertThat(parking.getPassengerCarSize(), is(0));
        assertThat(parking.getTruckCarSize(), is(1));
    }

    @Test
    public void whenTruckPakingOnPassengerPlace() {
        Transport auto = new PassengerCar();
        Transport truck = new TruckCar(10);
        Parking parking = new ParkingTransport(20, 0);
        parking.add(auto);
        parking.add(truck);
        assertThat(parking.getPassengerCarSize(), is(9));
        assertThat(parking.getTruckCarSize(), is(0));
        parking.delete(truck);
        assertThat(parking.getPassengerCarSize(), is(19));
        assertThat(parking.getTruckCarSize(), is(0));
    }
}