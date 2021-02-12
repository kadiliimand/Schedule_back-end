package com.example.demo.dataclasses;

import java.math.BigDecimal;

public class ScheduleReport {
    private int id;
    private int salaryCode;
    private BigDecimal hourlyPay;
    private double workedHours;
    private int emptyRow;
    private String departmentCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSalaryCode() {
        return salaryCode;
    }

    public void setSalaryCode(int salaryCode) {
        this.salaryCode = salaryCode;
    }

    public BigDecimal getHourlyPay() {
        return hourlyPay;
    }

    public void setHourlyPay(BigDecimal hourlyPay) {
        this.hourlyPay = hourlyPay;
    }

    public double getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(double workedHours) {
        this.workedHours = workedHours;
    }

    public int getEmptyRow() {
        return emptyRow;
    }

    public void setEmptyRow(int emptyRow) {
        this.emptyRow = emptyRow;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }
}
