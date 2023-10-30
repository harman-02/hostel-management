package com.hms.HostelManagement.repository;

import com.hms.HostelManagement.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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



    public void createStudent(Student s){
        String sql = "insert into student(roll,name,email,phone,branch,balance,dob) values (?, ?, ?,?,?,?,?)";
        template.update(sql,s.getRoll(),s.getName(),s.getEmail(),s.getPhone(),s.getBranch(),s.getBalance(),s.getDob());
    }

    public Student getStudentFromRoll(Integer roll) {
        String sql = "SELECT * FROM student WHERE roll = ?";
        try {
            return template.queryForObject(sql, new Object[]{roll},new BeanPropertyRowMapper<>(Student.class));
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void updateStudentFromRoll(Student s,Integer roll){
        System.out.println(s.getRoll()+" "+s.getName()+" "+s.getEmail()+" "+s.getBranch()+" "+s.getDob()+s.getBalance());
        String sql="UPDATE student SET name=?,email=?,phone=?,branch=?,dob=? where roll=?";
        template.update(sql,s.getName(),s.getEmail(),s.getPhone(),s.getBranch(),s.getDob(),roll);
    }
    public List<Student> getAll() {
        String sql ="select * from student";
        return template.query(sql, new BeanPropertyRowMapper<>(Student.class));
    }
}
