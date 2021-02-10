package com.example.demo.service;

import com.example.demo.dataclasses.Employee;
import com.example.demo.dataclasses.EmployeeNames;
import com.example.demo.dataclasses.Schedule;
import com.example.demo.errorHandling.ScheduleException;
import com.example.demo.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.*;


@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;


    @Transactional
    public String createEmployee(String idNumber, String name, String departmentCode,
                                 BigDecimal hourlyPay, int salaryCode, String password) {
        scheduleRepository.createEmployee(idNumber, name, departmentCode, hourlyPay, salaryCode, password);
        return "New employee has been created!";
    }

    @Transactional
    public List<Employee> getAllEmployeesData() {
        return scheduleRepository.getAllEmployeesData();
    }

    @Transactional
    public String updateEmployeeData(int id, String idNumber, String name, String departmentCode, BigDecimal hourlyPay, int salaryCode, String password) {
        scheduleRepository.updateEmployeeData(id, idNumber, name, departmentCode, hourlyPay, salaryCode, password);
        return "All data is updated!";
    }

    @Transactional
    public List<EmployeeNames> getAllEmployeesNames() {
        return scheduleRepository.getAllEmployeesNames();
    }

    @Transactional
    public void createSchedule(String name, Date date, Time startTime, Time endTime) {
        String idNumber = scheduleRepository.getEmployeeId(name);
        if (idNumber == null) {
            throw new ScheduleException("No such name");
        } else {
            scheduleRepository.createSchedule(idNumber, date, startTime, endTime);
        }
    }

    public String getEmployeeId(String name) {
        return scheduleRepository.getEmployeeId(name);
    }

    public List<Schedule> getEmployeeScheduleData(String name, Date dateFrom, Date dateTo) {
        String idNumber = scheduleRepository.getEmployeeId(name);
        return scheduleRepository.getEmployeeScheduleData(idNumber, dateFrom, dateTo);
    }

    public String changeScheduleRow(int id, String name, Date date, Time startTime, Time endTime) {
        String idNumber = scheduleRepository.getEmployeeId(name);
        scheduleRepository.changeScheduleRow(id, name, date, startTime, endTime);
        return "Schedule change successful!";
    }

    public String deleteEmployeeScheduleRow(int id) {
        scheduleRepository.deleteEmployeeScheduleRow(id);
        return "Work shift deleted!";
    }
}

