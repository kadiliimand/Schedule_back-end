package com.example.demo.repositories;

import com.example.demo.dataclasses.OneEmployeeReport;
import com.example.demo.dataclasses.Schedule;
import com.example.demo.dataclasses.ScheduleReport;
import com.example.demo.dataclasses.ScheduleWithNames;
import com.example.demo.errorHandling.ScheduleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository

public class ScheduleRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void createSchedule(String idNumber, LocalDate date, LocalTime startTime, LocalTime endTime, int salaryCode) {
        String sql = "INSERT INTO working_hours (wh_id_number, date, start_time, end_time, worked_time, wh_salary_code) " +
                "VALUES (:id, :date, :startTime, :endTime, :workedTime, :salaryCode)";
        Duration workedTime = Duration.between(startTime, endTime);
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("id", idNumber);
        paraMap.put("date", date);
        paraMap.put("startTime", startTime);
        paraMap.put("endTime", endTime);
        paraMap.put("workedTime", workedTime.getSeconds()/60.00);
        paraMap.put("salaryCode", salaryCode);
        jdbcTemplate.update(sql, paraMap);
    }

    public int checkScheduleRowId(int whRowId) {
        try {
            String sql = "SELECT wh_id FROM working_hours WHERE wh_id = :whRowId";
            Map<String, Object> paraMap = new HashMap<>();
            paraMap.put("whRowId", whRowId);
            return jdbcTemplate.queryForObject(sql, paraMap, int.class);
        } catch (EmptyResultDataAccessException e) {
            throw new ScheduleException("Invalid ID.");
        }
    }

    public void changeScheduleRow(int id, String id_number, LocalDate date, LocalTime startTime, LocalTime endTime) {
        String sql = "UPDATE  working_hours SET wh_id_number= :idNumber, date=:date, " +
                "start_time= :startTime, end_time= :endTime, worked_time= :workedTime WHERE wh_id=:shiftId ";
        Duration workedTime = Duration.between(startTime, endTime);
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("shiftId", id);
        paraMap.put("idNumber", id_number);
        paraMap.put("date", date);
        paraMap.put("startTime", startTime);
        paraMap.put("endTime", endTime);
        paraMap.put("workedTime", ((double) workedTime.getSeconds())/60.00);
        jdbcTemplate.update(sql, paraMap);
    }

    public List<ScheduleWithNames> getEmployeeScheduleData(String idNumber, LocalDate dateFrom, LocalDate dateTo){
        String sql = "SELECT * FROM employee e LEFT JOIN working_hours w ON e.id_number = w.wh_id_number WHERE e.id_number = :idNumber " +
                "AND date >= :dateFrom AND date <= :dateTo ORDER BY date ASC";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("idNumber", idNumber);
        paraMap.put("dateFrom", dateFrom);
        paraMap.put("dateTo", dateTo);
        return jdbcTemplate.query(sql, paraMap, new ScheduleWithNamesRowMapper());
    }

    private class ScheduleRowMapper implements RowMapper<Schedule> {
        @Override
        public Schedule mapRow(ResultSet resultSet, int i) throws SQLException {
            Schedule shift = new Schedule();
            shift.setId(resultSet.getInt("id"));
            shift.setDate(resultSet.getDate("date"));
            shift.setStartTime(resultSet.getTime("start_time"));
            shift.setEndTime(resultSet.getTime("end_time"));
            shift.setWorkedHours(resultSet.getInt("worked_time")/60.00);
            shift.setIdNumber(resultSet.getString("wh_id_number"));
            return shift;
        }
    }

    private class ScheduleWithNamesRowMapper implements RowMapper<ScheduleWithNames> {
        @Override
        public ScheduleWithNames mapRow(ResultSet resultSet, int i) throws SQLException {
            ScheduleWithNames shift = new ScheduleWithNames();
            shift.setWhId(resultSet.getInt("wh_id"));
            shift.setDate(resultSet.getDate("date"));
            shift.setStartTime(resultSet.getTime("start_time"));
            shift.setEndTime(resultSet.getTime("end_time"));
            shift.setWorkedHours(resultSet.getInt("worked_time")/60.00);
            shift.setName(resultSet.getString("name"));
            return shift;
        }
    }

    public void deleteEmployeeScheduleRow(int id) {
        String sql = "DELETE FROM working_hours WHERE wh_id = :shiftId";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("shiftId", id);
        jdbcTemplate.update(sql, paraMap);
    }

    public List<Schedule> getAllEmployeesScheduleData(LocalDate dateFrom, LocalDate dateTo) {
       String sql = "SELECT * FROM working_hours WHERE date >= :dateFrom AND date <= :dateTo";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("dateFrom", dateFrom);
        paraMap.put("dateTo", dateTo);
        return jdbcTemplate.query(sql, paraMap, new ScheduleRowMapper());
    }

    public List<ScheduleReport> getScheduleReport(LocalDate dateFrom, LocalDate dateTo){
        String sql = "SELECT employee.id_number, wh.wh_salary_code, employee.hourly_pay, SUM(wh.worked_time)/60.00 AS worked_hours, " +
                "employee.department_code FROM employee INNER JOIN working_hours wh ON employee.id_number = " +
                "wh.wh_id_number WHERE date >= :dateFrom AND date <= :dateTo GROUP BY employee.id_number, wh.wh_salary_code";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("dateFrom", dateFrom);
        paraMap.put("dateTo", dateTo);
        return jdbcTemplate.query(sql, paraMap, new ScheduleReportRowMapper());
    }

    private class ScheduleReportRowMapper implements RowMapper<ScheduleReport> {
        @Override
        public ScheduleReport mapRow(ResultSet resultSet, int i) throws SQLException {
            ScheduleReport report = new ScheduleReport();
            report.setIdNumber(resultSet.getString("id_number"));
            report.setSalaryCode(resultSet.getInt("wh_salary_code"));
            report.setHourlyPay(resultSet.getBigDecimal("hourly_pay"));
            report.setWorkedHours(resultSet.getDouble("worked_hours"));
            report.setEmptyRow("");
            report.setDepartmentCode(resultSet.getString("department_code"));
            return report;
        }
    }

    public List<OneEmployeeReport> getWorkHourSumForOneName(String name, LocalDate dateFrom, LocalDate dateTo){
        String sql = "SELECT employee.name, wh.wh_salary_code, SUM(wh.worked_time)/60.00 AS worked_hours " +
                "FROM employee INNER JOIN working_hours wh ON employee.id_number = wh.wh_id_number WHERE date >= :dateFrom " +
                "AND date <= :dateTo AND name = :name GROUP BY name, wh_salary_code ORDER BY wh_salary_code ASC";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("name", name);
        paraMap.put("dateFrom", dateFrom);
        paraMap.put("dateTo", dateTo);
        return jdbcTemplate.query(sql, paraMap, new WorkHourSumForOneNameRowMapper());
    }

    private class WorkHourSumForOneNameRowMapper implements RowMapper<OneEmployeeReport> {
        @Override
        public OneEmployeeReport mapRow(ResultSet resultSet, int i) throws SQLException {
            OneEmployeeReport report = new OneEmployeeReport();
            report.setName(resultSet.getString("name"));
            report.setSalaryCode(resultSet.getInt("wh_salary_code"));
            report.setWorkedHours(resultSet.getDouble("worked_hours"));
            return report;
        }
    }

    public String getScheduleReportToCSV(LocalDate dateFrom, LocalDate dateTo){
        String sql = "SELECT employee.id_number, wh.wh_salary_code, employee.hourly_pay, SUM(wh.worked_time)/60.00 AS worked_hours, " +
                "employee.department_code FROM employee INNER JOIN working_hours wh ON employee.id_number = " +
                "wh.wh_id_number WHERE date >= :dateFrom AND date <= :dateTo GROUP BY employee.id_number, wh.wh_salary_code";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("dateFrom", dateFrom);
        paraMap.put("dateTo", dateTo);
        return jdbcTemplate.queryForObject(sql, paraMap, String.class);
    }
}
