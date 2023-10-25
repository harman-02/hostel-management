package com.hms.HostelManagement.repository;

import com.hms.HostelManagement.model.HostelRegistration;
import com.hms.HostelManagement.model.MessCancellations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public class MessCancellationsRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    RowMapper<MessCancellations> rowMapper = (rs, rowNum) -> {
        MessCancellations messCancellations = new MessCancellations();
        messCancellations.setEntryNo(rs.getInt("entryNo"));
        messCancellations.setRollNo(rs.getInt("rollNo"));
        messCancellations.setHostelRegistrationId(rs.getInt("hostelRegistrationId"));
        messCancellations.setDate(rs.getDate("date_"));
        return messCancellations;
    };


    public void createMessCancellation(MessCancellations messCancellations, HostelRegistration hostelRegistration){
        String sql1= "SELECT hostel_registration_id FROM Hostel_registration " +
                "WHERE hostel_id = ? AND session = ?";
        Date date=messCancellations.getDate();
        System.out.println(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        System.out.println("Year: " + year);
        int hostelRegistrationId = jdbcTemplate.queryForObject(sql1, Integer.class,hostelRegistration.getHostelId(),date);
        System.out.println(hostelRegistrationId);
        Date utildate = date;
        java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());
        messCancellations.setDate(sqlDate);
        String sql2="insert into MessCancellations(hostelRegistrationId,rollno,date_) values (?,?,?)";
        jdbcTemplate.update(sql2, hostelRegistrationId,messCancellations.getRollNo(),messCancellations.getDate());
    }
    public List<MessCancellations> getAll() {
        String sql = "select * from MessCancellations";
        return jdbcTemplate.query(sql, rowMapper);
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
    public List<MessCancellations> balanceByRollNoAndSession(Integer rollNo,Integer year) {
        String sql = "select * from MessCancellations where rollNo = ? and YEAR(date_) = ?";
        return jdbcTemplate.query(sql, new Object[]{rollNo, year}, rowMapper);
    }
}
