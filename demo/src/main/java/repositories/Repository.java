package repositories;

import dataclasses.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//Kadi kommenteerib prooviks

//@repositories.Repository

public class Repository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public void newEmployee(String name, String idNumber, String departmentCode, BigDecimal hourlyPay,
                            int salaryCode, String password) {
        String sql = "INSERT INTO employee (id_number, name, department_code, hourly_pay, salary_code, password) " +
                "VALUES (:id, :name, :departmentCode, :hourlyPay, :salaryCode, :password)";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("id", idNumber);
        paraMap.put("name", name);
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

    public void changeSchedule(String id, String id_number, Date date, Time startTime, Time endTime) {
        String sql = "UPDATE  working_hours SET id_number= :id_number, date=:date, " +
                "start_time=:start_time, end_time= :end_time WHERE id=:shiftId ";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("id_number", id_number);
        paraMap.put("date", date);
        paraMap.put("start_time", startTime);
        paraMap.put("end_time", endTime);
        jdbcTemplate.update(sql, paraMap);
    }

    public void getEmployeeId(String name) {
        String sql = "SELECT id_number FROM employee WHERE name = :nameParam";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("nameParam", name);
        jdbcTemplate.update(sql, paraMap);
    }

    public void getEmployeeScheduleData(String id_number, Date date) {
        String sql = "SELECT id_number, date, start_time, end_time FROM " +
                "working_hours WHERE date = :date AND id_number = :id_number";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("date", date);
        paraMap.put("id_number", id_number);
        jdbcTemplate.update(sql, paraMap);
    }

    public void deleteEmployeeScheduleData(String id) {
        String sql = "DELETE ROW FROM working_hours WHERE id = :shiftId";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("shiftId", id);
        jdbcTemplate.update(sql, paraMap);
    }

}
