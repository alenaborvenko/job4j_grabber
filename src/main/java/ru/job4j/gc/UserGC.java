package ru.job4j.gc;

import static com.carrotsearch.sizeof.RamUsageEstimator.sizeOf;

public class UserGC {
    public static void main(String[] args) {
        //System.out.println(sizeOf(new User("-1", "N-1")) + " kb");
        for (int i = 0; i < 1_000_000; i++) {
            new User(String.valueOf(i), "N" + i);
        }
    }
}
