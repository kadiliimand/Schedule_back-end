package com.example.demo.repositories;

import com.example.demo.dataclasses.Employee;
import com.example.demo.dataclasses.EmployeeNames;
import com.example.demo.dataclasses.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.*;

@Repository

public class ScheduleRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public void createEmployee(String idNumber, String name, String departmentCode, BigDecimal hourlyPay,
                            int salaryCode, String password) {
        String sql = "INSERT INTO employee (name, id_number, department_code, hourly_pay, salary_code, password) " +
                "VALUES (:name, :idNumber, :departmentCode, :hourlyPay, :salaryCode, :password)";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("name", name);
        paraMap.put("idNumber", idNumber);
        paraMap.put("departmentCode", departmentCode);
        paraMap.put("hourlyPay", hourlyPay);
        paraMap.put("salaryCode", salaryCode);
        paraMap.put("password", password);
        jdbcTemplate.update(sql, paraMap);
    }

    public String getEmployeeId(String name) {
        String sql = "SELECT id_number FROM employee WHERE name = :nameParam";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("nameParam", name);
        jdbcTemplate.update(sql, paraMap);
        return jdbcTemplate.queryForObject(sql, paraMap, String.class );
    }

    public void updateEmployeeData(int id, String idNumber, String name, String departmentCode, BigDecimal hourlyPay,
                                  int salaryCode, String password) {
        String sql = "UPDATE  employee SET id_number= :id_number, name = :name, " +
                "department_code = :departmentCode, hourly_pay = :hourlyPay, salary_code = :salaryCode, password = :password WHERE id=:id";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("id", id);
        paraMap.put("name", name);
        paraMap.put("idNumber", idNumber);
        paraMap.put("departmentCode", departmentCode);
        paraMap.put("hourlyPay", hourlyPay);
        paraMap.put("salaryCode", salaryCode);
        paraMap.put("password", password);
        jdbcTemplate.update(sql, paraMap);
    }

    public List<EmployeeNames> getAllEmployeesNames() {
        String sql = "SELECT name FROM employee";
        return jdbcTemplate.query(sql, new HashMap<>(), new EmployeeRowMapper());
    }

    private class EmployeeRowMapper implements RowMapper{
        @Override
        public Employee mapRow(ResultSet resultSet, int i) throws SQLException{
            Employee person = new Employee();
            person.setName(resultSet.getString("name"));
            return person;
        }
    }

    public List<Employee> getAllEmployeesData(){
        String sql = "SELECT * FROM employee";
        return jdbcTemplate.query(sql, new HashMap<>(), new EmployeesRowMapper());
    }

    private class EmployeesRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
            Employee person = new Employee();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setIdNumber(resultSet.getString("id_number"));
            person.setDepartmentCode(resultSet.getString("department_code"));
            person.setHourlyPay(resultSet.getBigDecimal("hourly_pay"));
            person.setSalaryCode(resultSet.getInt("salary_code"));
            person.setPassword(resultSet.getString("password"));
            return person;
        }
    }

    public void createSchedule(String id, Date date, Time startTime, Time endTime) {
        String sql = "INSERT INTO working_hours (id, date, start_time, end_time) " +
                "VALUES (:id, :date, :start_time, :end_time)";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("id", id);
        paraMap.put("date", date);
        paraMap.put("start_time", startTime);
        paraMap.put("end_time", endTime);
        jdbcTemplate.update(sql, paraMap);
    }

    public void changeScheduleRow(int id, String id_number, Date date, Time startTime, Time endTime) {
        String sql = "UPDATE  working_hours SET id_number= :id_number, date=:date, " +
                "start_time=:start_time, end_time= :end_time WHERE id=:shiftId ";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("shiftId", id);
        paraMap.put("id_number", id_number);
        paraMap.put("date", date);
        paraMap.put("start_time", startTime);
        paraMap.put("end_time", endTime);
        jdbcTemplate.update(sql, paraMap);
    }

    public List<Schedule> getEmployeeScheduleData(String id_number, Date dateFrom, Date dateTo){
        String sql = "SELECT id, id_number, date, start_time, end_time FROM working_hours WHERE idNumber = :idNumber AND date >= :dateFrom AND date <= :dateTo";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("idNumber", id_number);
        paraMap.put("date", dateFrom);
        paraMap.put("date", dateTo);
        return jdbcTemplate.query(sql, paraMap, new ScheduleRowMapper());
    }

    private class ScheduleRowMapper implements RowMapper<Schedule> {
        @Override
        public Schedule mapRow(ResultSet resultSet, int i) throws SQLException {
            Schedule shift = new Schedule();
            shift.setId(resultSet.getInt("id"));
            shift.setDateFrom(resultSet.getDate("date"));
            shift.setDateTo(resultSet.getDate("date"));
            shift.setStartTime(resultSet.getTime("start_time"));
            shift.setEndTime(resultSet.getTime("end_time"));
            shift.setIdNumber(resultSet.getString("id_number"));
            return shift;
        }
    }

    public void deleteEmployeeScheduleRow(int id) {
        String sql = "DELETE ROW FROM working_hours WHERE id = :shiftId";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("shiftId", id);
        jdbcTemplate.update(sql, paraMap);
    }

//    public List<Schedule> getAllEmployeesScheduleData(Date dateFrom, Date dateTo) {
//        String sql = "SELECT * FROM working_hours WHERE date >= :dateFrom AND date <= :dateTo";
//        paraMap.put("date", dateFrom);
//        paraMap.put("date", dateTo);
//        return jdbcTemplate.query(sql, paraMap, new ScheduleRowMapper());
//    }

}
