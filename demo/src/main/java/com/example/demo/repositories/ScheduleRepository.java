package com.example.demo.repositories;

import com.example.demo.dataclasses.Schedule;
import com.example.demo.dataclasses.ScheduleReport;
import com.example.demo.dataclasses.ScheduleWithNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

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

//    @Autowired
//    private PasswordEncoder passwordEncoder;



    public void createSchedule(String idNumber, LocalDate date, LocalTime startTime, LocalTime endTime) {
        String sql = "INSERT INTO working_hours (id_number, date, start_time, end_time, worked_time) " +
                "VALUES (:id, :date, :start_time, :end_time, :workedTime)";
        Duration workedTime = Duration.between(startTime, endTime);
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("id", idNumber);
        paraMap.put("date", date);
        paraMap.put("start_time", startTime);
        paraMap.put("end_time", endTime);
        paraMap.put("workedTime", workedTime.getSeconds()/60.00);
        jdbcTemplate.update(sql, paraMap);
    }

    public void changeScheduleRow(int id, String id_number, LocalDate date, LocalTime startTime, LocalTime endTime) {
        String sql = "UPDATE  working_hours SET id_number= :id_number, date=:date, " +
                "start_time=:start_time, end_time= :end_time, worked_time= :workedTime WHERE wh_id=:shiftId ";
        Duration workedTime = Duration.between(startTime, endTime);

        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("shiftId", id);
        paraMap.put("id_number", id_number);
        paraMap.put("date", date);
        paraMap.put("start_time", startTime);
        paraMap.put("end_time", endTime);
        paraMap.put("workedTime", ((double) workedTime.getSeconds())/60.00);
        jdbcTemplate.update(sql, paraMap);
    }

    public List<ScheduleWithNames> getEmployeeScheduleData(String idNumber, LocalDate dateFrom, LocalDate dateTo){
        String sql = "SELECT * FROM employee e LEFT JOIN working_hours w ON e.id_number = w.wh_id_number WHERE e.id_number = :idNumber " +
                "AND date >= :dateFrom AND date <= :dateTo";
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
            shift.setId(resultSet.getInt("wh_id"));
            shift.setDate(resultSet.getDate("date"));
            shift.setStartTime(resultSet.getTime("start_time"));
            shift.setEndTime(resultSet.getTime("end_time"));
            shift.setWorkedHours(resultSet.getInt("worked_time")/60.00);
            shift.setIdNumber(resultSet.getString("wh_id_number"));
            return shift;
        }
    }

    public List<ScheduleWithNames> getAllEmployeeScheduleDataWithNames(LocalDate dateFrom, LocalDate dateTo){
        String sql = "SELECT * FROM employee e LEFT JOIN working_hours w ON e.id_number = w.wh_id_number " +
                "WHERE date >= :dateFrom AND date <= :dateTo ORDER BY date ASC ";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("dateFrom", dateFrom);
        paraMap.put("dateTo", dateTo);
        return jdbcTemplate.query(sql, paraMap, new ScheduleWithNamesRowMapper());
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
        String sql = "DELETE ROW FROM working_hours WHERE wh_id = :shiftId";
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

    public List<ScheduleWithNames> getScheduleDataWithNames() {
        String sql = "SELECT * FROM working_hours";
        List<ScheduleWithNames> scheduleWithNamesList = jdbcTemplate.query(sql, new HashMap<>(), new ScheduleWithNamesRowMapper());
        return scheduleWithNamesList;
    }

    public List<ScheduleReport> getScheduleReport(LocalDate dateFrom, LocalDate dateTo){
        String sql = "SELECT employee.id_number, wh.wh_salary_code, employee.hourly_pay, SUM(wh.worked_time)/60.00 AS, " +
                "employee.department_code FROM employee INNER JOIN working_hours wh ON employee.id_number = " +
                "wh.wh_id_number WHERE date >= :dateFrom AND date <= :dateTo GROUP BY employee.id_number, wh_salary_code";
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
            report.setWorkedHours(resultSet.getDouble("?column?")); //kuidas see summa SQL-ist kätte saada?
            report.setEmptyRow("");
            report.setDepartmentCode(resultSet.getString("department_code"));
            return report;
        }
    }


}
