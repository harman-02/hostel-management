package com.hms.HostelManagement.repository;

import com.hms.HostelManagement.model.StudentUserMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentUserMappingRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

//    public StudentUserMapping getRollNoFromUsername(String username)
//    {
//        String sql = "Select * from Student where userID=?";
//        return jdbcTemplate.queryForObject(sql,new Object[]{username}, new BeanPropertyRowMapper<>(StudentUserMapping.class));
//    }
}
