package repositories;

import dataclasses.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.math.BigDecimal;
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
        String sql = "INSERT INTO employee (idNumber, name, departmentCode, hourlyPay, salaryCode, password) " +
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
/*
    public void createSchedule(String idNumber, Date date, Time st,
                            int salaryCode, String password) {
        String sql = "INSERT INTO employee (idNumber, name, departmentCode, hourlyPay, salaryCode, password) " +
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
    */

}
