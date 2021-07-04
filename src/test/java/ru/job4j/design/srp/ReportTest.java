package ru.job4j.design.srp;

import com.google.gson.Gson;
import org.junit.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ReportTest {
    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petya", now, now, 200);
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getHired()).append(";")
                .append(worker2.getFired()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenProgrammGenerate() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petya", now, now, 100);
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportProgrammer(store);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE HTML>")
                .append("<html>/<head><meta charset=\"utf-8\"><title>Employee</title>")
                .append("</head><body><table border=\"1\">")
                .append("<tr>")
                .append("<th>Name</th")
                .append("<th>Hired</th>")
                .append("<th>Fired</th>")
                .append("<th>Salary</th></tr>")
                .append("<tr><td>").append(worker.getName()).append("</td></tr>")
                .append("<tr><td>").append(worker.getHired()).append("</td></tr>")
                .append("<tr><td>").append(worker.getFired()).append("</td></tr>")
                .append("<tr><td>").append(worker.getSalary()).append("</td></tr>")
                .append("<tr><td>").append(worker2.getName()).append("</td></tr>")
                .append("<tr><td>").append(worker2.getHired()).append("</td></tr>")
                .append("<tr><td>").append(worker2.getFired()).append("</td></tr>")
                .append("<tr><td>").append(worker2.getSalary()).append("</td></tr>")
                .append("</table></body></html>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHRGenerate() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petya", now, now, 200);
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportHR(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenAccountingGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petya", now, now, 200);
        store.add(worker);
        store.add(worker2);
        double exhangeEuro = 87.07;
        Report engine = new ReportAccounting(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary() / exhangeEuro).append(";")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getHired()).append(";")
                .append(worker2.getFired()).append(";")
                .append(worker2.getSalary() / exhangeEuro).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenGenerateJson() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petya", now, now, 200);
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportJSON(store);
        Gson gson = new Gson();
        StringBuilder expect = new StringBuilder()
                .append(gson.toJson(worker))
                .append(System.lineSeparator())
                .append(gson.toJson(worker2))
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenGenerateXML() throws DatatypeConfigurationException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petya", now, now, 200);
        store.add(worker);
        store.add(worker2);
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(now.getTime());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance()
                .newXMLGregorianCalendar(c);
        Report engine = new ReportXML(store);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n")
                .append("<employees>\n")
                .append("    <employee>\n")
                .append("        <name>").append(worker.getName()).append("</name>\n")
                .append("        <hired>").append(xmlDate).append("</hired>\n")
                .append("        <fired>").append(xmlDate).append("</fired>\n")
                .append("        <salary>").append(worker.getSalary()).append("</salary>\n")
                .append("    </employee>\n")
                .append("    <employee>\n")
                .append("        <name>").append(worker2.getName()).append("</name>\n")
                .append("        <hired>").append(xmlDate).append("</hired>\n")
                .append("        <fired>").append(xmlDate).append("</fired>\n")
                .append("        <salary>").append(worker2.getSalary()).append("</salary>\n")
                .append("    </employee>\n")
                .append("</employees>\n");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}