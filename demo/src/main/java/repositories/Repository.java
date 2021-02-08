package repositories;

import dataclasses.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    public void changeSchedule(String id, Date date, Time startTime, Time endTime) {
        String sql = "UPDATE  working_hours SET id=:id, date=:date, " +
                "start_time=:start_time, end_time= :end_time WHERE id=:id ";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("id", id);
        paraMap.put("date", date);
        paraMap.put("start_time", startTime);
        paraMap.put("end_time", endTime);
        jdbcTemplate.update(sql, paraMap);
    }


}
