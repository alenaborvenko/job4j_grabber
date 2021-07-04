package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportProgrammer implements Report {
    private Store store;

    public ReportProgrammer(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder rsl = new StringBuilder()
                .append("<!DOCTYPE HTML>")
                .append("<html>/<head><meta charset=\"utf-8\"><title>Employee</title>")
                .append("</head><body><table border=\"1\">")
                .append("<tr>")
                .append("<th>Name</th")
                .append("<th>Hired</th>")
                .append("<th>Fired</th>")
                .append("<th>Salary</th></tr>");
        for (var employee : store.findBy(filter)) {
            rsl.append("<tr><td>").append(employee.getName()).append("</td></tr>");
            rsl.append("<tr><td>").append(employee.getHired()).append("</td></tr>");
            rsl.append("<tr><td>").append(employee.getFired()).append("</td></tr>");
            rsl.append("<tr><td>").append(employee.getSalary()).append("</td></tr>");
        }
        rsl.append("</table></body></html>");
        return rsl.toString();
    }
}
