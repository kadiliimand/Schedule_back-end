package com.example.demo.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScheduleResponse {
    private final List<String> names = new ArrayList<>();
    private final List<ScheduleRow> table = new ArrayList<>();

    public ScheduleResponse(Map<Date, Map<String, TimePair>> rows) {
        for (Map.Entry<Date, Map<String, TimePair>> dateMapEntry : rows.entrySet()) {
            Map<String, TimePair> x = dateMapEntry.getValue();
            for (String s : x.keySet()) {
                if(!names.contains(s)){
                    names.add(s);
                }
            }
        }

        for (Date date : rows.keySet()) {
            table.add(new ScheduleRow(date, rows.get(date)));
        }
    }

    public List<String> getNames() {
        return names;
    }

    public List<ScheduleRow> getTable() {
        return table;
    }

    private class ScheduleRow {
        private final Date date;
        private final List<EmployeeTimes> employeeTimesList = new ArrayList<>();

        public ScheduleRow(Date date, Map<String, TimePair> stringTimePairMap) {
            this.date = date;
            for (String name : names) {
                employeeTimesList.add(new EmployeeTimes(stringTimePairMap.get(name)));
            }
        }

        public Date getDate() {
            return date;
        }

        public List<EmployeeTimes> getEmployeeTimesList() {
            return employeeTimesList;
        }
    }

    private class EmployeeTimes {
        private Time fromTime = null;
        private Time toTime = null;

        public EmployeeTimes(TimePair timePair) {
            if(timePair != null){
                this.fromTime = timePair.getFromTime();
                this.toTime = timePair.getToTime();
            }
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
}
