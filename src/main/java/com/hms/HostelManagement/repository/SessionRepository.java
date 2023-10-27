package com.hms.HostelManagement.repository;
import com.hms.HostelManagement.model.Hostel;
import com.hms.HostelManagement.model.Session;
import com.hms.HostelManagement.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SessionRepository {
    @Autowired
    private JdbcTemplate template;

    public void createSession(Session session){
        String sql = "insert into Session(session_name, start_date) values (?, ?)";
        template.update(sql, session.getSessionName(), session.getStartDate());
    }

    public List<Session> getAllSession(){
        String sql = "select * from session";
        return template.query(sql, new BeanPropertyRowMapper<>(Session.class));
    }

    public Session getSessionFromId(int id){
        String sql="select * from session where session_id=?";
        return template.queryForObject(sql,new Object[]{id},new BeanPropertyRowMapper<>(Session.class));
    };

}
