package com.example.demo.repositories;

import com.example.demo.dataclasses.Schedule;
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
                "start_time=:start_time, end_time= :end_time, worked_time= :workedTime WHERE id=:shiftId ";
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

    public List<Schedule> getEmployeeScheduleData(String idNumber, LocalDate dateFrom, LocalDate dateTo){
        String sql = "SELECT id, id_number, date, start_time, end_time, worked_time FROM working_hours WHERE id_number = :idNumber " +
                "AND date >= :dateFrom AND date <= :dateTo";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("idNumber", idNumber);
        paraMap.put("dateFrom", dateFrom);
        paraMap.put("dateTo", dateTo);
        return jdbcTemplate.query(sql, paraMap, new ScheduleRowMapper());
    }

    private class ScheduleRowMapper implements RowMapper<Schedule> {
        @Override
        public Schedule mapRow(ResultSet resultSet, int i) throws SQLException {
            double workedHours = resultSet.getInt("worked_time");
            Schedule shift = new Schedule();
            shift.setId(resultSet.getInt("id"));
            shift.setDate(resultSet.getDate("date"));
            shift.setStartTime(resultSet.getTime("start_time"));
            shift.setEndTime(resultSet.getTime("end_time"));
            shift.setWorkedHours(resultSet.getInt("worked_time")/60.00);
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

    public List<Schedule> getAllEmployeesScheduleData(LocalDate dateFrom, LocalDate dateTo) {
       String sql = "SELECT * FROM working_hours WHERE date >= :dateFrom AND date <= :dateTo";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("dateFrom", dateFrom);
        paraMap.put("dateTo", dateTo);
        return jdbcTemplate.query(sql, paraMap, new ScheduleRowMapper());
    }

    public List<Schedule> getAllEmployeesScheduleDataWithName(LocalDate dateFrom, LocalDate dateTo) {
        String sql = "SELECT * FROM working_hours WHERE date >= :dateFrom AND date <= :dateTo";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("dateFrom", dateFrom);
        paraMap.put("dateTo", dateTo);
        return jdbcTemplate.query(sql, paraMap, new ScheduleRowMapper());
    }

    public List<Schedule> getScheduleData() {
        String sql = "SELECT * FROM working_hours";
        List<Schedule> scheduleList = jdbcTemplate.query(sql, new HashMap<>(), new ScheduleRowMapper());
        return scheduleList;
    }

}
