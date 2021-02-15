package com.example.demo.dataclasses;

public class OneEmployeeReport {
    private String name;
    private int salaryCode;
    private double workedHours;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalaryCode() {
        return salaryCode;
    }

    public void setSalaryCode(int salaryCode) {
        this.salaryCode = salaryCode;
    }

    public double getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(double workedHours) {
        this.workedHours = workedHours;
    }
}
