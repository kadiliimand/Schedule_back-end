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

//    @CrossOrigin
//    @GetMapping("allEmployeesNames")
//    public List<Employee> allEmployeesNames(){
//        return scheduleService.allEmployeesNames();
//    }
//
//    @CrossOrigin
//    @PostMapping("createSchedule")
//    public void createSchedule(@RequestParam("name") String name, @RequestParam("date") Date date, @RequestParam("startTime") Time startTime, @RequestParam("endTime") Time endTime) {
//        return scheduleService.createSchedule(name, date, startTime, endTime);
//    }
//
////    @CrossOrigin
////    @PutMapping("changeSchedule")
////    public String changeSchedule()
//
//    @CrossOrigin
//    @DeleteMapping("deleteEmployeeScheduleData")
//    public String deleteEmployeeScheduleData(){
//
//    }
//
//    @CrossOrigin
//    @GetMapping("getEmployeeScheduleData")
//    public List<Schedule> getEmployeeScheduleData(@RequestParam("name") String name, @RequestParam("dateFrom") Date dateFrom, @RequestParam("dateTo") Date dateTo){
//        return scheduleService.getEmployeeScheduleData(name, dateFrom, dateTo);
//    }
}
