package com.hms.HostelManagement.repository;

import com.hms.HostelManagement.model.AllMessCancellations;
import com.hms.HostelManagement.model.MessCancellations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class MessCancellationsRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    RowMapper<MessCancellations> rowMapper = (rs, rowNum) -> {
        MessCancellations messCancellations = new MessCancellations();
        messCancellations.setEntryNo(rs.getInt("entryNo"));
        messCancellations.setHostelRegistrationId(rs.getInt("hostelRegistrationId"));
        messCancellations.setRollNo(rs.getInt("rollNo"));
        messCancellations.setDate(rs.getDate("date_"));
        return messCancellations;
    };

    RowMapper<AllMessCancellations> rowMapper1 = (rs, rowNum) -> {
        AllMessCancellations allMessCancellations = new AllMessCancellations();
        allMessCancellations.setEntryNo(rs.getInt("entryNo"));
        allMessCancellations.setHostelId(rs.getInt("hostel_id"));
        allMessCancellations.setHostelName(rs.getString("hostel_name"));
        allMessCancellations.setStudentRollNo(rs.getInt("rollNo"));
        allMessCancellations.setStudentName(rs.getString("name"));
        allMessCancellations.setDate(rs.getDate("date_"));
        allMessCancellations.setSessionId(rs.getInt("session_id"));
        allMessCancellations.setSessionStartDate(rs.getDate("start_date"));
        return allMessCancellations;
    };


    public void createMessCancellation(MessCancellations messCancellations) {
        String sql = "insert into MessCancellations(hostelRegistrationId, rollNo, date_) values (?, ?, ?)";
        jdbcTemplate.update(sql, messCancellations.getHostelRegistrationId(), messCancellations.getRollNo(), messCancellations.getDate());
    }

    public List<AllMessCancellations> getAll() {
        String sql = "select m.entryNo, H.hostel_id, H.hostel_name, m.rollNo, S.name, m.date_, s2.session_id , s2.start_date\n" +
                "from MessCancellations m\n" +
                "inner join Student S on m.rollNo = S.roll\n" +
                "inner join Hostel_registration Hr on m.hostelRegistrationId = Hr.hostel_registration_id\n" +
                "inner join Hostel H on Hr.hostel_id = H.hostel_id\n" +
                "inner join Session S2 on Hr.session = S2.session_id";
        return jdbcTemplate.query(sql, rowMapper1);
    }

    public MessCancellations getById(Integer id) {
        String sql = "select * from MessCancellations where entryNo = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
    }

    public void updateMessCancellations(MessCancellations messCancellations) {
        String sql = "update MessCancellations set date_ = ? where entryNo = ?";
        jdbcTemplate.update(sql, messCancellations.getDate(), messCancellations.getEntryNo());
    }

    public void deleteMessCancellations(Integer entryNo) {
        String sql = "delete from MessCancellations where entryNo = ?";
        jdbcTemplate.update(sql, entryNo);
    }

    public List<MessCancellations> filterById(Integer hostelRegistrationid) {
        String sql = "select * from MessCancellations where hostelRegistrationId = ?";
        return jdbcTemplate.query(sql, new Object[]{hostelRegistrationid}, rowMapper);
    }

    public List<MessCancellations> filterBySession(int year) {
        String sql = "select * from MessCancellations where YEAR(date_)= ?";
        return jdbcTemplate.query(sql, new Object[]{year}, rowMapper);
    }
    public List<MessCancellations> filterBySessionAndhostel(Integer hostelRegistrationid, int year) {
        String sql = "select * from MessCancellations where hostelRegistrationid=? and YEAR(date_)= ?";
        return jdbcTemplate.query(sql, new Object[]{hostelRegistrationid, year}, rowMapper);
    }
    public List<MessCancellations> filterByRollNo(Integer rollNo) {
        String sql = "select * from MessCancellations where rollNo = ?";
        return jdbcTemplate.query(sql, new Object[]{rollNo}, rowMapper);
    }
    public List<MessCancellations> filterByDate(Date start, Date end) {
        String sql = "select * from MessCancellations where date_ >= ? and date_ <= ?";
        return jdbcTemplate.query(sql, new Object[]{start, end}, rowMapper);
    }
    public List<MessCancellations> filterByRollNoAndSession(Integer rollNo, Integer year) {
        String sql = "select * from MessCancellations where rollNo = ? and YEAR(date_) = ?";
        return jdbcTemplate.query(sql, new Object[]{rollNo, year}, rowMapper);
    }
}
