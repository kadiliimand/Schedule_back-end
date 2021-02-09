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

    @PostMapping("createEmployee")
    public String createEmployee(@RequestParam("name") String name, @RequestParam("id") String idNumber, @RequestParam("dep") String departmentCode, @RequestParam("pay") BigDecimal hourlyPay, @RequestParam("salaryCode") int salaryCode, @RequestParam("password") String password){
        return scheduleService.createEmployee(name, idNumber, departmentCode, hourlyPay, salaryCode, password);
    }

    @GetMapping("allEmployeesNames")
    public List<Employee> allEmployeesNames(){
        return ;
    }

    @PostMapping("createSchedule")
    public void createSchedule(@RequestParam("name") String name, @RequestParam("date") Date date, @RequestParam("start") Time startTime, @RequestParam("end") Time endTime){
        return;
    }

    @PutMapping("changeSchedule")
    public void changeSchedule()

}
