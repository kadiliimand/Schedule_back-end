package com.example.demo.controller;

import com.example.demo.dataclasses.Employee;
import com.example.demo.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@RestController

public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @CrossOrigin
    @PostMapping("createEmployee")
    public String createEmployee(@RequestParam("idNumber") String idNumber, @RequestParam("name") String name, @RequestParam("departmentCode") String departmentCode, @RequestParam("hourlyPay") BigDecimal hourlyPay, @RequestParam("salaryCode") int salaryCode, @RequestParam("password") String password) {
        return scheduleService.createEmployee(idNumber, name, departmentCode, hourlyPay, salaryCode, password);
    }

    @GetMapping("allEmployeesNames")
    public List<Employee> allEmployeesNames(){
        return scheduleService.allEmployeesNames;
    }

    @PostMapping("createSchedule")
    public void createSchedule(@RequestParam("name") String name, @RequestParam("date") Date date, @RequestParam("start") Time startTime, @RequestParam("end") Time endTime) {
        return;
    }

//    @PutMapping("changeSchedule")
//    public void changeSchedule()

}
