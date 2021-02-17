package com.example.demo.service;

import com.example.demo.dataclasses.ScheduleReport;
import com.example.demo.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class JsonToCSVService {
//    private static final String COMMA_DELIMITER = ",";
//    private static final String LINE_SEPARATOR = "\n";
//
//    private static final String HEADER = "isikukood, ,, ,tasu kood,tasu määr,kogus, ,osakond";

    @Autowired
    private ScheduleRepository scheduleRepository;

//    public void getScheduleReport(LocalDate dateFrom, LocalDate dateTo, HttpServletResponse response){
//        byte[] report = scheduleRepository.
//        response.setHeader("")
//                response.getOutputStream().write(report);
//    }
    {



//        FileWriter fileWriter = null;
//
//        try
//        {
//            fileWriter = new FileWriter("MonthlyReport.csv");
//
//            //Adding the header
//            fileWriter.append(HEADER);
//            //New Line after the header
//            fileWriter.append(LINE_SEPARATOR);
//
//            //Iterate the empList
//            Iterator it = scheduleRepository.getScheduleReport(dateFrom, dateTo).iterator();
//            while(it.hasNext())
//            {
//                ScheduleReport sr = (ScheduleReport) it.next();
//                fileWriter.append(sr.getIdNumber());
//                fileWriter.append(COMMA_DELIMITER);
//                fileWriter.append(sr.getEmptyRow());
//                fileWriter.append(COMMA_DELIMITER);
//                fileWriter.append(sr.getEmptyRow());
//                fileWriter.append(COMMA_DELIMITER);
//                fileWriter.append(String.valueOf(sr.getSalaryCode()));
//                fileWriter.append(COMMA_DELIMITER);
//                fileWriter.append(String.valueOf(sr.getHourlyPay()));
//                fileWriter.append(COMMA_DELIMITER);
//                fileWriter.append(String.valueOf(sr.getWorkedHours()));
//                fileWriter.append(COMMA_DELIMITER);
//                fileWriter.append(sr.getEmptyRow());
//                fileWriter.append(COMMA_DELIMITER);
//                fileWriter.append(String.valueOf(sr.getDepartmentCode()));
//                fileWriter.append(LINE_SEPARATOR);
//            }
//            return "Write to CSV file Succeeded.";
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        finally
//        {
//            try
//            {
//                fileWriter.close();
//            }
//            catch(IOException ie)
//            {
//                return "Error occured while closing the fileWriter";
//                ie.printStackTrace();
//            }
//        }
//        return ;
    }
}
