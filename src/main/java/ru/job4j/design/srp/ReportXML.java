package ru.job4j.design.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportXML implements Report {
    @XmlTransient
    private Store store;

    @XmlElement(name = "employee")
    private List<Employee> employeeList;

    public ReportXML() {
    }

    public ReportXML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringWriter rsl = new StringWriter();
        try {
            JAXBContext context = JAXBContext.newInstance(ReportXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            employeeList = store.findBy(filter);
            marshaller.marshal(this, rsl);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return rsl.toString();
    }
}
