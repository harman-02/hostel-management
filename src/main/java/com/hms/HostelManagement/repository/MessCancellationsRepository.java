package com.hms.HostelManagement.repository;

import com.hms.HostelManagement.model.MessCancellations;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class MessCancellationsRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createMessCancellation (MessCancellations messCancellations) {
        String sql = "insert into MessCancellations(hostelRegistrationId, rollNo, date_) values (?, ?, ?)";
        jdbcTemplate.update(sql, messCancellations.getHostelRegistrationId(), messCancellations.getRollNo(), messCancellations.getDate());
    }

    public List<MessCancellations> getAll() {
        String sql = "select * from MessCancellations";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MessCancellations.class));
    }

    public MessCancellations getById(int id) {
        String sql = "select * from MessCancellations where entryNo = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>());
    }

    public void updateMessCancellations(MessCancellations messCancellations) {
        String sql = "update MessCancellations set hostelRegistrationId = ?, rollNo = ?, date_ = ? where entryNo = ?";
        jdbcTemplate.update(sql, messCancellations.getHostelRegistrationId(), messCancellations.getRollNo(), messCancellations.getDate(), messCancellations.getEntryNo());
    }

    public void deleteMessCancellations(Integer entryNo) {
        String sql = "delete from MessCancellations where entryNo = ?";
        jdbcTemplate.update(sql, entryNo);
    }
}
