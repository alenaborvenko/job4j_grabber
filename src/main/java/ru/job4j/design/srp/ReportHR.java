package ru.job4j.design.srp;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportHR implements Report {
    private Store store;

    public ReportHR(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder rsl = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator());
        List<Employee> employeesFind = store.findBy(filter);
        employeesFind.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        for (var employee : employeesFind) {
            rsl.append(employee.getName()).append(";");
            rsl.append(employee.getSalary()).append(";");
            rsl.append(System.lineSeparator());
        }
        return rsl.toString();
    }
}
