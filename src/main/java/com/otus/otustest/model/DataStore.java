package com.otus.otustest.model;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.UnmarshallerProperties;

public class DataStore {

    private static final Logger LOG = Logger.getLogger(DataStore.class.getName());

    private static DataStore INSTANCE;

    private List<Student> students;
    @XmlJavaTypeAdapter(MapAdapter.class)
    private Map<String, String> classes;

    static {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(DataStore.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
            unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, false);
            StreamSource source = new StreamSource(DataStore.class.getResourceAsStream("/students_classes.json"));
            INSTANCE = unmarshaller.unmarshal(source, DataStore.class).getValue();
            AtomicInteger nextId = new AtomicInteger();
            INSTANCE.students.stream().forEachOrdered(s -> s.setId(nextId.getAndIncrement()));
        } catch (JAXBException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public static DataStore getInstance() {
        return INSTANCE;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Map<String, String> getClasses() {
        return classes;
    }

    public void setClasses(Map<String, String> classes) {
        this.classes = classes;
    }

    public List<Student> findStudentByFirstAndLast(String first, String last) {
        return students.stream().filter(s -> ((first == null) || s.getFirst().equals(first)) && ((last == null) || s.getLast().equals(last))).collect(Collectors.toList());
    }

    public Student getStudent(int id) {
        return students.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    public String getClassName(int id) {
        return classes.get(Integer.toString(id));
    }

}
