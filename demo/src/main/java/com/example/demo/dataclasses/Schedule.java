package com.example.demo.dataclasses;

import java.sql.Date;
import java.sql.Time;


public class Schedule {
    private int id;
    private Date dateFrom;
    private Date dateTo;
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

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
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
