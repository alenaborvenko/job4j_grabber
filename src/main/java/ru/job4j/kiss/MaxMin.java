package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return findByComparator(value, comparator);
    }

    private <T> T findByComparator(List<T> value, Comparator<T> comparator) {
        T rsl = value.get(0);
        for (T el : value) {
            if (comparator.compare(rsl, el) > 0) {
                rsl = el;
            }
        }
        return rsl;
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return findByComparator(value, comparator.reversed());
    }
}
