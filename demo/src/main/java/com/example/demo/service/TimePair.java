package com.example.demo.service;

import java.sql.Time;

public class TimePair {
    private Time fromTime;
    private Time toTime;

    public TimePair(Time startTime, Time endTime) {
        this.fromTime = startTime;
        this.toTime = endTime;
    }

    public Time getFromTime() {
        return fromTime;
    }

    public void setFromTime(Time fromTime) {
        this.fromTime = fromTime;
    }

    public Time getToTime() {
        return toTime;
    }

    public void setToTime(Time toTime) {
        this.toTime = toTime;
    }
}
