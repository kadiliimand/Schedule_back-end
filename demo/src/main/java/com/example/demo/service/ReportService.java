package com.example.demo.service;

import com.example.demo.dataclasses.ScheduleWithNames;
import com.example.demo.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public ScheduleResponse getAllEmployeeScheduleDataWithNamesReport(LocalDate dateFrom, LocalDate dateTo) {
        List<ScheduleWithNames> response = reportRepository.getAllEmployeeScheduleDataWithNamesReport(dateFrom, dateTo);
        Map<Date, Map<String, TimePair>> rows = new HashMap();
        for (ScheduleWithNames element : response) {
            Date date = element.getDate();
            Map<String, TimePair> rowEmployeeMap = rows.get(date);
            if(rowEmployeeMap == null) {
                rowEmployeeMap = new HashMap<>();
                rows.put(date, rowEmployeeMap);
            }
            rowEmployeeMap.put(element.getName(), new TimePair(element.getStartTime(), element.getEndTime()));
        }
        Map<Date, Map<String, TimePair>> sortedRows = new TreeMap<Date, Map<String, TimePair>>(rows);
        return new ScheduleResponse(sortedRows);
    }
//    https://stackoverflow.com/questions/21974407/how-to-stream-a-json-object-to-a-httpurlconnection-post-request

}
