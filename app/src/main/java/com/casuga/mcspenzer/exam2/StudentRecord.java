package com.casuga.mcspenzer.exam2;

public class StudentRecord {
    String student_firstName, student_lastName;
    double student_average;

    public StudentRecord() {
    }

    public StudentRecord(String student_firstName, String student_lastName, double student_average) {
        this.student_firstName = student_firstName;
        this.student_lastName = student_lastName;
        this.student_average = student_average;
    }

    public String getStudent_firstName() {
        return student_firstName;
    }

    public void setStudent_firstName(String student_firstName) {
        this.student_firstName = student_firstName;
    }

    public String getStudent_lastName() {
        return student_lastName;
    }

    public void setStudent_lastName(String student_lastName) {
        this.student_lastName = student_lastName;
    }

    public double getStudent_average() {
        return student_average;
    }

    public void setStudent_average(double student_average) {
        this.student_average = student_average;
    }
}
