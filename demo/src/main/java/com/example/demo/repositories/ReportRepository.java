package com.example.demo.repositories;

import com.example.demo.dataclasses.ScheduleWithNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReportRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    public List<ScheduleWithNames> getAllEmployeeScheduleDataWithNamesReport(LocalDate dateFrom, LocalDate dateTo){
        String sql = "SELECT * FROM employee e LEFT JOIN working_hours w ON e.id_number = w.wh_id_number " +
                "WHERE date >= :dateFrom AND date <= :dateTo ORDER BY date ASC ";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("dateFrom", dateFrom);
        paraMap.put("dateTo", dateTo);
        return jdbcTemplate.query(sql, paraMap, new ScheduleWithNamesReportRowMapper());
    }

    private class ScheduleWithNamesReportRowMapper implements RowMapper<ScheduleWithNames> {
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
}
