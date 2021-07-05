package ru.job4j.design.srp;

import com.google.gson.Gson;

import java.util.function.Predicate;

public class ReportJSON implements Report {
    private Store store;

    public ReportJSON(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder rsl = new StringBuilder();
        Gson gson = new Gson();
        rsl.append(gson.toJson(store.findBy(filter)));
        return rsl.toString();
    }
}
