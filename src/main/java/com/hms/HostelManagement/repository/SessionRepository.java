package com.hms.HostelManagement.repository;

import com.hms.HostelManagement.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SessionRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createSession(@org.jetbrains.annotations.NotNull Session session){
        String sql = "insert into Session(session_name, start_date) values (?, ?)";
        Session session1 = session;
        jdbcTemplate.update(sql, session1.getSessionName(), session1.getStartDate());
        System.out.println(session1);
    }

    public List<Session> getAllSession(){
        String sql = "select * from Session";
        List<Session> sessionList = jdbcTemplate.query(sql, (resultSet, i) -> {
            Session session = new Session();
            session.setSessionId(resultSet.getInt("session_id"));
            session.setSessionName(resultSet.getString("session_name"));
            session.setStartDate(resultSet.getDate("start_date"));
            return session;
        });
        return sessionList;
    }
}
