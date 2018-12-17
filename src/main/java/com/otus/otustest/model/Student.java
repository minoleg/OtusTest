package com.otus.otustest.model;

import java.util.List;

public class Student {

    private int id;
    private String first;
    private String last;
    private String email;
    private List<StudentClass> studentClasses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getAverageGrade() {
        return studentClasses.stream().mapToDouble(sc -> sc.getGrade()).average().getAsDouble();
    }

    public List<StudentClass> getStudentClasses() {
        return studentClasses;
    }

    public void setStudentClasses(List<StudentClass> studentClasses) {
        this.studentClasses = studentClasses;
    }
    
    public static class StudentClass {

        private int id;
        private double grade;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getGrade() {
            return grade;
        }

        public void setGrade(double grade) {
            this.grade = grade;
        }

    }

}
