package com.otus.otustest.rs;

import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SearchResult {

    private List<Student> students;

    public SearchResult() {
    }

    public SearchResult(List<com.otus.otustest.model.Student> studentList) {
        students = studentList.stream().map(s -> {
            SearchResult.Student student = new SearchResult.Student();
            student.setId(s.getId());
            student.setFirstName(s.getFirst());
            student.setLastName(s.getLast());
            student.setAverageGrade(s.getAverageGrade());
            return student;
        }).collect(Collectors.toList());
    }

    public void setStudent(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudent() {
        return students;
    }

    public static class Student {

        private int id;
        private String firstName;
        private String lastName;
        private double averageGrade;

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

        public double getAverageGrade() {
            return averageGrade;
        }

        public void setAverageGrade(double averageGrade) {
            this.averageGrade = averageGrade;
        }

    }

}
