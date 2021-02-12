package com.example.demo.repositories;


import com.example.demo.dataclasses.Employee;
import com.example.demo.dataclasses.EmployeeNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

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
        return jdbcTemplate.queryForObject(sql, paraMap, String.class);
    }

    public String getEmployeeName(String idNumber) {
        String sql = "SELECT name FROM employee WHERE idNumber = :idNumberParam";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("idNumberParam", idNumber);
        return jdbcTemplate.queryForObject(sql, paraMap, String.class);
    }

    public void updateEmployeeData(int id, String idNumber, String name, String departmentCode, BigDecimal hourlyPay,
                                   int salaryCode, String password) {
        String sql = "UPDATE  employee SET id_number= :id_number, name = :name, " +
                "department_code = :departmentCode, hourly_pay = :hourlyPay, salary_code = :salaryCode, password = :password WHERE id=:id";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("id", id);
        paraMap.put("name", name);
        paraMap.put("id_Number", idNumber);
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

    private class EmployeeRowMapper implements RowMapper {
        @Override
        public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
            Employee person = new Employee();
            person.setName(resultSet.getString("name"));
            return person;
        }
    }
//test
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
}
