package com.example.demo.controller;

import com.example.demo.dataclasses.Employee;
import com.example.demo.dataclasses.EmployeeNames;
import com.example.demo.dataclasses.Schedule;
import com.example.demo.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController

public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @CrossOrigin
    @PostMapping("createEmployee")
    public String createEmployee(@RequestParam("idNumber") String idNumber, @RequestParam("name") String name,
                                 @RequestParam("departmentCode") String departmentCode,
                                 @RequestParam("hourlyPay") BigDecimal hourlyPay, @RequestParam("salaryCode") int salaryCode,
                                 @RequestParam("password") String password) {
        return scheduleService.createEmployee(idNumber, name, departmentCode, hourlyPay, salaryCode, password);
    }

    @CrossOrigin
    @PutMapping("updateEmployeeData")
    public String updateEmployeeData(@RequestParam("id") int id, @RequestParam("idNumber") String idNumber,
                                     @RequestParam("name") String name, @RequestParam("departmentCode") String departmentCode,
                                     @RequestParam("hourlyPay") BigDecimal hourlyPay, @RequestParam("salaryCode") int salaryCode,
                                     @RequestParam("password") String password){
        return scheduleService.updateEmployeeData(id, idNumber, name, departmentCode, hourlyPay, salaryCode, password);
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
    public void createSchedule(@RequestParam("name") String name, @RequestParam("date") Date date,
                               @RequestParam("startTime") Time startTime, @RequestParam("endTime") Time endTime) {
        scheduleService.createSchedule(name, date, startTime, endTime);
    }

    @CrossOrigin
    @PutMapping("changeScheduleRow")
    public String changeScheduleRow(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("date") Date date,
                                 @RequestParam("startTime") Time startTime, @RequestParam("endTime") Time endTime){
        return scheduleService.changeScheduleRow(id, name, date, startTime, endTime);
    }

    @CrossOrigin
    @DeleteMapping("deleteEmployeeScheduleRow")
    public String deleteEmployeeScheduleRow(@RequestParam("id") int id){
        return scheduleService.deleteEmployeeScheduleRow(id);
    }

    @CrossOrigin
    @GetMapping("getEmployeeScheduleData")
    public List<Schedule> getEmployeeScheduleData(@RequestParam("name") String name, @RequestParam("dateFrom") Date dateFrom, @RequestParam("dateTo") Date dateTo){
        return scheduleService.getEmployeeScheduleData(name, dateFrom, dateTo);
    }
}
