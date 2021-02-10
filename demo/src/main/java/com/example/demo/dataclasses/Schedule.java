package com.example.demo.dataclasses;

import java.sql.Date;
import java.sql.Time;


public class Schedule {
    private int id;
    private Date date;
    private Time startTime;
    private Time endTime;
    private int workAtTime;
    private String idNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getWorkAtTime() {
        return workAtTime;
    }

    public void setWorkAtTime(int workAtTime) {
        this.workAtTime = workAtTime;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
}
