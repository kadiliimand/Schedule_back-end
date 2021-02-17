package com.example.demo.service;

import com.example.demo.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class JsonToCSVService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public void getScheduleReportToCSV(LocalDate dateFrom, LocalDate dateTo, HttpServletResponse response) throws IOException {
        String str = scheduleRepository.exportFileToString(dateFrom, dateTo);
        byte[] report = str.getBytes();
        response.setContentType(str);
        response.setContentLength(report.length);
        response.getOutputStream().write(report);
    }
}
