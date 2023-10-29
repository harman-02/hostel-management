package com.hms.HostelManagement.repository;

import com.hms.HostelManagement.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class SessionRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<Session> getAll() {
        String sql = "select * from Session";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Session.class));
    }
}
