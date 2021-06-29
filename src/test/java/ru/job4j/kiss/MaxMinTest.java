package ru.job4j.kiss;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MaxMinTest {

    @Test
    public void max() {
        List<String> data = new ArrayList<>(List.of(
                "ivan",
                "petr",
                "denis"
        ));
        String actual = new MaxMin().max(data, Comparator.comparing(String::toString));
        String expected = "denis";
        assertThat(actual, is(expected));
    }

    @Test
    public void min() {
        List<String> data = new ArrayList<>(List.of(
                "ivan",
                "petr",
                "denis"
        ));
        String actual = new MaxMin().min(data, Comparator.comparing(String::toString));
        String expected = "petr";
        assertThat(actual, is(expected));
    }
}