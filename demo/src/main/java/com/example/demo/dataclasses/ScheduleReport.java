package com.example.demo.dataclasses;

import java.math.BigDecimal;

public class ScheduleReport {

    private String idNumber;
    private int salaryCode;
    private BigDecimal hourlyPay;
    private double workedHours;
    private String emptyRow;
    private String departmentCode;

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
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

    public String getEmptyRow() {
        return emptyRow;
    }

    public void setEmptyRow(String emptyRow) {
        this.emptyRow = emptyRow;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }
}
