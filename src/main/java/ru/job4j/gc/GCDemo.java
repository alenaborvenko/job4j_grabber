package ru.job4j.gc;

public class GCDemo {
    private static final long KB = 1000L;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("====Environment=====");
        System.out.printf("Free: %d%n", freeMemory / MB);
        System.out.printf("Total memory: %d%n", totalMemory / MB);
        System.out.printf("Max memory: %d%n", maxMemory / MB);
    }

    public static void main(String[] args) {
        info();
        for (int i = 0; i < 100000000000000L; i++) {
            new Person(i, "N" + i);
            System.gc();
        }
        info();
    }
}
