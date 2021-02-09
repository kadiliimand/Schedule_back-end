package com.example.demo.repositories;

import com.example.demo.dataclasses.Employee;
import com.example.demo.dataclasses.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void changeSchedule(String id, String id_number, Calendar date, Time startTime, Time endTime) {
        String sql = "UPDATE  working_hours SET id_number= :id_number, date=:date, " +
                "start_time=:start_time, end_time= :end_time WHERE id=:shiftId ";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("id_number", id_number);
        paraMap.put("date", date);
        paraMap.put("start_time", startTime);
        paraMap.put("end_time", endTime);
        jdbcTemplate.update(sql, paraMap);
    }

    public String getEmployeeId(String name) {
        String sql = "SELECT id_number FROM employee WHERE name = :nameParam";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("nameParam", name);
        jdbcTemplate.update(sql, paraMap);
        return jdbcTemplate.queryForObject(sql, paraMap, String.class );
    }

    public List<Schedule> getEmployeeScheduleData(String id_number, Calendar date){
        String sql = "SELECT id_number, date, start_time, end_time FROM working_hours WHERE date = :date AND id_number = :id_number";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("date", date);
        paraMap.put("id_number", id_number);
        return jdbcTemplate.query(sql, paraMap, new ScheduleRowMapper());
    }

    private class ScheduleRowMapper implements RowMapper<Schedule> {
        @Override
        public Schedule mapRow(ResultSet resultSet, int i) throws SQLException {
            Schedule shift = new Schedule();
            shift.setId(resultSet.getInt("id"));
            shift.setDate(resultSet.getDate("date"));
            shift.setStartTime(resultSet.getTime("start_time"));
            shift.setEndTime(resultSet.getTime("end_date"));
            shift.setIdNumber(resultSet.getString("id_number"));
            return shift;
        }
    }

    public List<Schedule> allEmployeesScheduleData(Date date) {
        String sql = "SELECT * FROM working_hours WHERE date = :date";
        return jdbcTemplate.query(sql, new HashMap<>(), new ScheduleRowMapper());
    }

    public List<Employee> allEmployeesNames() {
        String sql = "SELECT * FROM employee";
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

    public void deleteEmployeeScheduleData(String id) {
        String sql = "DELETE ROW FROM working_hours WHERE id = :shiftId";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("shiftId", id);
        jdbcTemplate.update(sql, paraMap);
    }

}
