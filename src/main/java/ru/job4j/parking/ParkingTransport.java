package ru.job4j.parking;

public class ParkingTransport implements Parking {
    private int sizeParking;

    public ParkingTransport(int sizeParking) {
        this.sizeParking = sizeParking;
    }

    @Override
    public int getPlace() {
        return sizeParking;
    }

    @Override
    public void takeFreePlace(Transport transport) {
        sizeParking -= transport.getSize();
    }

    @Override
    public void add(Transport transport) {
        if (sizeParking < transport.getSize()) {
            throw new IllegalArgumentException("Нет места на парковке. Ждите пока "
                    + "кто-нибудь уедет");
        }
        takeFreePlace(transport);
    }

    @Override
    public void delete(Transport transport) {
        sizeParking += transport.getSize();
    }
}
