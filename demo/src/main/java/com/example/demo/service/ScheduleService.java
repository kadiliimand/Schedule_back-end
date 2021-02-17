package com.example.demo.service;

import com.example.demo.dataclasses.*;
import com.example.demo.errorHandling.ScheduleException;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public String createEmployee(String idNumber, String name, String departmentCode,
                                 BigDecimal hourlyPay, String password) {
        if (employeeRepository.canAddEmployee(idNumber)) {
            employeeRepository.createEmployee(idNumber, name, departmentCode, hourlyPay, password);
            return "New employee has been created!";
        }
        return "Entered ID number already in use!";
    }

    @Transactional
    public List<Employee> getAllEmployeesData() {
        return employeeRepository.getAllEmployeesData();
    }

    @Transactional
    public String updateEmployeeData(int id, String idNumber, String name, String departmentCode, BigDecimal hourlyPay,
                                     String password) {
        if (employeeRepository.getEmployeeRowId(id) == 0) {
            throw new ScheduleException("Invalid employee system id number!");
        } else {
            employeeRepository.updateEmployeeData(id, idNumber, name, departmentCode, hourlyPay, password);
            return "All data is updated!";
        }
    }

    @Transactional
    public List<EmployeeNames> getAllEmployeesNames() {
        return employeeRepository.getAllEmployeesNames();
    }

    @Transactional
    public String createSchedule(String name, LocalDate date, LocalTime startTime, LocalTime endTime, int salaryCode) {
        String idNumber = employeeRepository.getEmployeeId(name);
        if (idNumber == null) {
            throw new ScheduleException("No such name");
        } else {
            scheduleRepository.createSchedule(idNumber, date, startTime, endTime, salaryCode);
            return "Shift created";
        }
    }

    public List<ScheduleWithNames> getEmployeeScheduleData(String name, LocalDate dateFrom, LocalDate dateTo) {
        String idNumber = employeeRepository.getEmployeeId(name);
        return scheduleRepository.getEmployeeScheduleData(idNumber, dateFrom, dateTo);
    }

    public List<Schedule> getAllEmployeesScheduleData(LocalDate dateFrom, LocalDate dateTo) {
        return scheduleRepository.getAllEmployeesScheduleData(dateFrom, dateTo);
    }

    public String changeScheduleRow(int id, String name, LocalDate date, LocalTime startTime, LocalTime endTime) {
        String idNumber = employeeRepository.getEmployeeId(name);
        scheduleRepository.checkScheduleRowId(id);
        scheduleRepository.changeScheduleRow(id, idNumber, date, startTime, endTime);
        return "Schedule change successful!";
    }

    public String deleteEmployeeScheduleRow(int id) {
        scheduleRepository.checkScheduleRowId(id);
        scheduleRepository.deleteEmployeeScheduleRow(id);
        return "Work shift deleted!";
    }

    public List<ScheduleReport> exportData(LocalDate dateFrom, LocalDate dateTo) {
        return scheduleRepository.getScheduleReport(dateFrom, dateTo);
    }

    public List<OneEmployeeReport> getWorkHourSumForOneName(String name, LocalDate dateFrom, LocalDate dateTo) {
        return scheduleRepository.getWorkHourSumForOneName(name, dateFrom, dateTo);
    }

}

