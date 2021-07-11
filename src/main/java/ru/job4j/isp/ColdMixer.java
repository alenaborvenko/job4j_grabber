package ru.job4j.isp;

public class ColdMixer implements WaterMixer {
    @Override
    public int getWarmWater() {
        return 0;
    }

    @Override
    public int getColdWater() {
        throw new UnsupportedOperationException();
    }
}
