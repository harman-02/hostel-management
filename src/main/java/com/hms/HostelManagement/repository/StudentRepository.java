package com.hms.HostelManagement.repository;

import com.hms.HostelManagement.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {
    @Autowired
    private JdbcTemplate template;

//
//    public void createStudent(Student student) {
//        String sql = "INSERT INTO student (username,password,role,rollNo, name, phone, dob, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        template.update(
//                sql, student.getUsername(), student.getPassword(), student.getRole(), student.getRollNo(),
//                student.getName(), student.getPhone(), student.getDob(),student.getEmail());
//    }
//
////    public List<Student> getAllInSession(int sessionId) {
////        String sql = "SELECT * FROM student WHERE sessionId = ?";
////        return template.query(sql, new Object[] {sessionId}, new BeanPropertyRowMapper<>(Student.class));
////    }
//
//    public Student getByRollNo(String rollNo) {
//        String sql = "SELECT * FROM student WHERE rollNo = ?";
//        return template.queryForObject(sql, new Object[] {rollNo}, new BeanPropertyRowMapper<>(Student.class));
//    }
//
//    public void delete(String rollNo) {
//        String sql = "DELETE FROM student WHERE rollNo = ?";
//        template.update(sql, rollNo);
//    }
//
//    public void update(Student student) {
//        String sql = "UPDATE student SET name=?, email=?, phone=?, dob=?, bio=?, address=? WHERE rollNo = ?";
//
//        template.update(
//                sql, student.getName(), student.getEmail(), student.getPhone(),
//                student.getDob(), student.getBio(), student.getAddress(),
//                student.getRollNo());
//    }
}
