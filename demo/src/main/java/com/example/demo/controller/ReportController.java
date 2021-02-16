package com.example.demo.controller;

import com.example.demo.service.ReportService;
import com.example.demo.service.ScheduleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    //http://localhost:8080/public/getAllEmployeeScheduleDataWithNamesReport?dateFrom=2021-02-11&dateTo=2021-02-14
    @CrossOrigin
    @GetMapping("public/getAllEmployeeScheduleDataWithNamesReport")
    public ScheduleResponse getAllEmployeeScheduleDataWithNamesReport(@RequestParam("dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
                                                                      @RequestParam("dateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo){
        return reportService.getAllEmployeeScheduleDataWithNamesReport(dateFrom, dateTo);
    }
}
