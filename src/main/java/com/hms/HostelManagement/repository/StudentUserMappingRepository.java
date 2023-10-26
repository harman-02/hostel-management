package com.hms.HostelManagement.repository;

import com.hms.HostelManagement.model.Student;
import com.hms.HostelManagement.model.StudentUserMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentUserMappingRepository {
    @Autowired
    JdbcTemplate template;

    public StudentUserMapping getStudentUserMappingFromUsername(String username)
    {
        String sql = "Select * from StudentUserMapping where username=?";
        return template.queryForObject(sql,new Object[]{username}, new BeanPropertyRowMapper<>(StudentUserMapping.class));
    }

    public Student getStudentFromUsername(String username){
        int roll=getStudentUserMappingFromUsername(username).getRoll();
        String sql = "SELECT * FROM student WHERE roll = ?";
        return template.queryForObject(sql, new Object[]{roll},new BeanPropertyRowMapper<>(Student.class));
    }

    public void createStudentUserMapping(StudentUserMapping s){
        String sql = "insert into StudentUserMapping(username,roll,hostelRegistrationId) values (?, ?, ?)";
        template.update(sql,s.getUsername(),s.getRoll(),s.getHostelRegistrationId());
    }



}
