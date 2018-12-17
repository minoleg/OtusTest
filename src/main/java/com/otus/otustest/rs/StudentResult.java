package com.otus.otustest.rs;

import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlRootElement;

import com.otus.otustest.model.DataStore;

@XmlRootElement
public class StudentResult {

    private int id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private double averageGrade;
    private List<StudentClass> classes;

    public StudentResult() {
    }

    public StudentResult(com.otus.otustest.model.Student student) {
        id = student.getId();
        firstName = student.getFirst();
        lastName = student.getLast();
        emailAddress = student.getEmail();
        averageGrade = student.getAverageGrade();
        classes = student.getStudentClasses().stream().map(s -> {
            StudentClass studentClass = new StudentClass();
            studentClass.setName(DataStore.getInstance().getClassName(s.getId()));
            return studentClass;
        }).collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public void setStudentClass(List<StudentClass> classes) {
        this.classes = classes;
    }

    public List<StudentClass> getStudentClass() {
        return classes;
    }

   public static class StudentClass {

        private String name;
        private double grade;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getGrade() {
            return grade;
        }

        public void setGrade(double grade) {
            this.grade = grade;
        }

   }

}
