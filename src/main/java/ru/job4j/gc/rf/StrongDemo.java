package ru.job4j.gc.rf;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StrongDemo {
    public static void main(String[] args) throws InterruptedException {
        //example1();
        //example2();
        example4();
        example5();
        //example3();
    }

    private static void example5() {
        List<SoftReference<Object>> objects = new ArrayList<>();
        for (int i = 0; i < 100_000_000; i++) {
            objects.add(new SoftReference<>(new Object() {
                private String value = String.valueOf(System.currentTimeMillis());

                @Override
                protected void finalize() {
                    System.out.println("Objects removed!");
                }
            }));
        }
        System.gc();
        int liveObject = 0;
        for (var ref : objects) {
            Object object = ref.get();
            if (object != null) {
                liveObject++;
            }
        }
        System.out.println(liveObject);
    }

    private static void example4() {
        Object strong = new Object();
        SoftReference<Object> soft = new SoftReference<>(strong);
        strong = null;
        System.out.println(soft.get());
    }

    private static void example1() throws InterruptedException {
        Object[] objects = new Object[100];
        for (int i = 0; i < objects.length; i++) {
            objects[i] = new Object() {

                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Object removed!");
                }
            };
        }
        for (int i = 0; i < objects.length; i++) {
            objects[i] = null;
        }
        System.gc();
        TimeUnit.SECONDS.sleep(5);
    }

    private static void example2() throws InterruptedException {
        Object[] objects = new Object[100];
        for (int i = 0; i < objects.length; i++) {
            Object object  = new Object() {
                private Object innnerObject = new Object() {
                    @Override
                    protected void finalize() throws Throwable {
                        System.out.println("Remove inner object!");
                    }
                };
            };
            objects[i] = object;
        }
        for (int i = 0; i < objects.length; i++) {
            objects[i] = null;
        }
        System.gc();
        TimeUnit.SECONDS.sleep(5);
    }

    private static void example3() {
        List<String> stringList = new ArrayList<>();
        while (true) {
            stringList.add(String.valueOf(System.currentTimeMillis()));
        }
    }
}
