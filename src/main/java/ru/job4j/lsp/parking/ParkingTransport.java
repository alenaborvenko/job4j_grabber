package ru.job4j.lsp.parking;

public class ParkingTransport implements Parking {
    private final int beginPassenger;
    private final int beginTruck;
    private int passengerCarSize;
    private int truckCarSize;

    public ParkingTransport(int passengerCarSize, int truckCarSize) {
        this.passengerCarSize = passengerCarSize;
        this.truckCarSize = truckCarSize;
        beginPassenger = passengerCarSize;
        beginTruck = truckCarSize;
    }

    @Override
    public int getPassengerCarSize() {
        return passengerCarSize;
    }

    @Override
    public int getTruckCarSize() {
        return truckCarSize;
    }

    @Override
    public void add(Transport transport) {
        int sizeCar = transport.getSize();
        if (sizeCar == 1) {
            if (sizeCar <= passengerCarSize) {
                passengerCarSize -= 1;
            } else {
                throw new IllegalArgumentException("Нет места на парковке. Ждите пока "
                        + "кто-нибудь уедет");
            }
        } else if (truckCarSize > 0) {
            truckCarSize -= 1;
        } else if (sizeCar <= passengerCarSize) {
            passengerCarSize -= sizeCar;
        } else {
            throw new IllegalArgumentException("Нет места на парковке. Ждите пока "
                    + "кто-нибудь уедет");
        }
    }

    @Override
    public void delete(Transport transport) {
        int sizeCar = transport.getSize();
        if (sizeCar == 1) {
            passengerCarSize += 1;
        } else {
            if (beginTruck >= truckCarSize + 1) {
                truckCarSize += 1;
            } else {
                passengerCarSize += sizeCar;
            }
        }
    }
}
