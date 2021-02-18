package com.example.demo.controller;

import com.example.demo.dataclasses.*;
import com.example.demo.service.JsonToCSVService;
import com.example.demo.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController

public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private JsonToCSVService jsonToCSVService;

    @CrossOrigin
    @PostMapping("public/createEmployee")
    public String createEmployee(@RequestParam("idNumber") String idNumber, @RequestParam("name") String name,
                                 @RequestParam("departmentCode") String departmentCode,
                                 @RequestParam("hourlyPay") BigDecimal hourlyPay,
                                 @RequestParam("password") String password) {
        return scheduleService.createEmployee(idNumber, name, departmentCode, hourlyPay, password);
    }

    @CrossOrigin
    @PutMapping("public/updateEmployeeData")
    public String updateEmployeeData(@RequestParam("id") int id, @RequestParam("idNumber") String idNumber,
                                     @RequestParam("name") String name, @RequestParam("departmentCode") String departmentCode,
                                     @RequestParam("hourlyPay") BigDecimal hourlyPay,
                                     @RequestParam("password") String password){
        return scheduleService.updateEmployeeData(id, idNumber, name, departmentCode, hourlyPay, password);
    }

    @CrossOrigin
    @GetMapping("public/getAllEmployeesData")
    public List<Employee> getAllEmployeesData(){
        return scheduleService.getAllEmployeesData();
    }

    @CrossOrigin
    @GetMapping("public/getAllEmployeesNames")
    public List<EmployeeNames> allEmployeesNames(){
        return scheduleService.getAllEmployeesNames();
    }

    @CrossOrigin
    @PostMapping("public/createSchedule")
    public String createSchedule(@RequestParam("name") String name,
                               @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                               @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
                               @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime,
                               @RequestParam("salaryCode") int salaryCode) {
        return scheduleService.createSchedule(name, date, startTime, endTime, salaryCode);
    }

    @CrossOrigin
    @PutMapping("public/changeScheduleRow")
    public String changeScheduleRow(@RequestParam("id") int id, @RequestParam("name") String name,
                                    @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                    @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
                                    @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime) {
        return scheduleService.changeScheduleRow(id, name, date, startTime, endTime);
    }

    @CrossOrigin
    @DeleteMapping("public/deleteEmployeeScheduleRow")
    public String deleteEmployeeScheduleRow(@RequestParam("id") int id){
        return scheduleService.deleteEmployeeScheduleRow(id);
    }

    @CrossOrigin
    @GetMapping("public/getEmployeeScheduleData")
    public List<ScheduleWithNames> getEmployeeScheduleData(@RequestParam("name") String name,
                                                  @RequestParam("dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
                                                  @RequestParam("dateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo){
        return scheduleService.getEmployeeScheduleData(name, dateFrom, dateTo);
    }

    @CrossOrigin
    @GetMapping("public/getAllEmployeesScheduleData")
    public List<Schedule> getAllEmployeesScheduleData(@RequestParam("dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
                                                      @RequestParam("dateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo){
        return scheduleService.getAllEmployeesScheduleData(dateFrom, dateTo);
    }

    @CrossOrigin
    @GetMapping("public/exportData")
    public List<ScheduleReport> exportData(@RequestParam("dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
                                           @RequestParam("dateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo){
        return scheduleService.exportData(dateFrom, dateTo);
    }

    @CrossOrigin
    @GetMapping("public/workHourSumForOneName")
    public List<OneEmployeeReport> workHourSumForOneName(@RequestParam("name") String name, @RequestParam("dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
                                           @RequestParam("dateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo){
        return scheduleService.getWorkHourSumForOneName(name, dateFrom, dateTo);
    }

    @CrossOrigin
    @GetMapping("public/testExportDataToCSV")
    public void testExportToCSV(@RequestParam("dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
                                @RequestParam("dateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
                                HttpServletResponse response) throws IOException {
        jsonToCSVService.getScheduleReportToCSV(dateFrom, dateTo, response);

    }
}

