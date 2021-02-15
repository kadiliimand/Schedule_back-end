package com.example.demo.repositories;


import com.example.demo.dataclasses.Employee;
import com.example.demo.dataclasses.EmployeeNames;
import com.example.demo.errorHandling.ScheduleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
                               String password) {
        String sql = "INSERT INTO employee (name, id_number, department_code, hourly_pay, password) " +
                "VALUES (:name, :idNumber, :departmentCode, :hourlyPay, :password)";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("name", name);
        paraMap.put("idNumber", idNumber);
        paraMap.put("departmentCode", departmentCode);
        paraMap.put("hourlyPay", hourlyPay);
        paraMap.put("password", password);
        jdbcTemplate.update(sql, paraMap);
    }

    public String getEmployeeId(String name) {
        try {
        String sql = "SELECT id_number FROM employee WHERE name = :nameParam";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("nameParam", name);
            return jdbcTemplate.queryForObject(sql, paraMap, String.class);
        }catch (EmptyResultDataAccessException e) {
            throw new ScheduleException("Employee not existing or spelled wrong.");
        }

    }

/*    public String getEmployeeName(String idNumber) {
        String sql = "SELECT name FROM employee WHERE idNumber = :idNumberParam";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("idNumberParam", idNumber);
        return jdbcTemplate.queryForObject(sql, paraMap, String.class);
    }*/

    public void updateEmployeeData(int id, String idNumber, String name, String departmentCode, BigDecimal hourlyPay,
                                   String password) {
        String sql = "UPDATE  employee SET id_number= :id_number, name = :name, " +
                "department_code = :departmentCode, hourly_pay = :hourlyPay, password = :password WHERE id=:id";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("id", id);
        paraMap.put("name", name);
        paraMap.put("id_number", idNumber);
        paraMap.put("departmentCode", departmentCode);
        paraMap.put("hourlyPay", hourlyPay);
        paraMap.put("password", password);
        jdbcTemplate.update(sql, paraMap);
    }

    public int getEmployeeRowId (int id){
        String sql = "SELECT id FROM employee WHERE id = :idParam";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("idParam", id);
        try{
            return jdbcTemplate.queryForObject(sql, paraMap, Integer.class);
        } catch (EmptyResultDataAccessException e){
            return 0;
        }
    }

    public boolean checkEmployeeIdNumberExistence (String idNumber) {
        String sql = "SELECT id_number FROM employee WHERE id_number = :idNrParam";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("idNrParam", idNumber);
        try {
            jdbcTemplate.queryForObject(sql, paraMap, String.class);
            return false;
             }
        catch (EmptyResultDataAccessException e){
            return true;
        }

    }


    public List<EmployeeNames> getAllEmployeesNames() {
        String sql = "SELECT name FROM employee";
        return jdbcTemplate.query(sql, new HashMap<>(), new EmployeeRowMapper());
    }

    private class EmployeeRowMapper implements RowMapper {
        @Override
        public EmployeeNames mapRow(ResultSet resultSet, int i) throws SQLException {
            EmployeeNames person = new EmployeeNames();
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
}
