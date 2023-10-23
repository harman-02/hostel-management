package com.hms.HostelManagement.repository;

import com.hms.HostelManagement.model.Student;
import com.hms.HostelManagement.model.User;
import com.hms.HostelManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {
    @Autowired
    private JdbcTemplate template;
    @Autowired
    private UserService userService;


    public void createStudent(Student student) {
        String sql = "INSERT INTO student (userID,roll, name, phone, dob, email,branch,balance) VALUES (?, ?, ?, ?, ?, ?,? ,? )";
        User user=userService.makeRandomUser("studentFirst");
        student.setUsername(user.getUsername());
        student.setBalance(0);
        template.update(sql, user.getUsername(),student.getRoll(), student.getName(), student.getPhone(), student.getDob(), student.getEmail(), student.getBranch(),student.getBalance());
    }


    public Student getByRollNo(String rollNo) {
        String sql = "SELECT * FROM student WHERE roll = ?";
        return template.queryForObject(sql, new Object[] {rollNo}, new BeanPropertyRowMapper<>(Student.class));
    }

    public void delete(Integer rollNo) {
        String sql = "DELETE FROM student WHERE roll = ?";
        template.update(sql, rollNo);
    }

    public void update(Student student) {
        String sql = "UPDATE student SET name=?, email=?, phone=?, dob=? WHERE roll = ?";

        template.update(
                sql, student.getName(), student.getEmail(), student.getPhone(),
                student.getDob(),
                student.getRoll());
    }
}
