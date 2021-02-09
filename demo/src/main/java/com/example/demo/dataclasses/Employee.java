package com.example.demo.dataclasses;

import java.math.BigDecimal;

public class Employee {


    private String name;
    private String idNumber;
    private String departmentCode;
    private BigDecimal hourlyPay;
    private int salaryCode;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public BigDecimal getHourlyPay() {
        return hourlyPay;
    }

    public void setHourlyPay(BigDecimal hourlyPay) {
        this.hourlyPay = hourlyPay;
    }

    public int getSalaryCode() {
        return salaryCode;
    }

    public void setSalaryCode(int salaryCode) {
        this.salaryCode = salaryCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
