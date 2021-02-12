package com.example.demo.controller;

import com.example.demo.dataclasses.Employee;
import com.example.demo.dataclasses.EmployeeNames;
import com.example.demo.dataclasses.Schedule;
import com.example.demo.dataclasses.ScheduleWithNames;
import com.example.demo.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController

public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @CrossOrigin
    @PostMapping("public/createEmployee")
    public String createEmployee(@RequestParam("idNumber") String idNumber, @RequestParam("name") String name,
                                 @RequestParam("departmentCode") String departmentCode,
                                 @RequestParam("hourlyPay") BigDecimal hourlyPay, @RequestParam("salaryCode") int salaryCode,
                                 @RequestParam("password") String password) {
        return scheduleService.createEmployee(idNumber, name, departmentCode, hourlyPay, salaryCode, password);
    }

    @CrossOrigin
    @PutMapping("public/updateEmployeeData")
    public String updateEmployeeData(@RequestParam("id") int id, @RequestParam("idNumber") String idNumber,
                                     @RequestParam("name") String name, @RequestParam("departmentCode") String departmentCode,
                                     @RequestParam("hourlyPay") BigDecimal hourlyPay, @RequestParam("salaryCode") int salaryCode,
                                     @RequestParam("password") String password){
        return scheduleService.updateEmployeeData(id, idNumber, name, departmentCode, hourlyPay, salaryCode, password);
    }
    //http://localhost:8080/public/getAllEmployeesData
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
    public void createSchedule(@RequestParam("name") String name,
                               @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                               @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
                               @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime) {
        scheduleService.createSchedule(name, date, startTime, endTime);
    }

    @CrossOrigin
    @PutMapping("public/changeScheduleRow")
    public String changeScheduleRow(@RequestParam("id") int id, @RequestParam("name") String name,
                                    @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date,
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
//http://localhost:8080/public/getAllEmployeesScheduleDataWithNames?dateFrom=2021-02-11&dateTo=2021-02-14
    @CrossOrigin
    @GetMapping("public/getAllEmployeesScheduleDataWithNames")
    public List<ScheduleWithNames> getAllEmployeesScheduleDataWithNames(@RequestParam("dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
                                                                        @RequestParam("dateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo){
        return scheduleService.getAllEmployeesScheduleDataWithNames(dateFrom, dateTo);
    }

    @CrossOrigin
    @GetMapping("public/getScheduleData")
    public List getScheduleData(){ return scheduleService.getScheduleData();}
}

