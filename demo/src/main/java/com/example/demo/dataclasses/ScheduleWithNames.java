package com.example.demo.dataclasses;

import java.sql.Date;
import java.sql.Time;

public class ScheduleWithNames {
    private int whId;
    private String name;
    private Date date;
    private Time startTime;
    private Time endTime;
    private double workedHours;
    private int salaryCode;


    public int getSalaryCode() {
        return salaryCode;
    }

    public void setSalaryCode(int salaryCode) {
        this.salaryCode = salaryCode;
    }

    public int getWhId() {
        return whId;
    }

    public void setWhId(int whId) {
        this.whId = whId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public double getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(double workedHours) {
        this.workedHours = workedHours;
    }



}
