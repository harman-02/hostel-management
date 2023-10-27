package com.hms.HostelManagement.repository;

import com.hms.HostelManagement.model.Complaint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;

@Repository
public class ComplaintRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public void createComplaint(Complaint complaint)
    {
        String sql = "INSERT INTO COMPLAINT(rollNo, description, date_of_complain, status) values (?,?,current_date(), 'Pending')";
        jdbcTemplate.update(sql, complaint.getRollNo(), complaint.getDescription());
    }

    public List<Complaint> getAllComplaint()
    {
        String sql = "SELECT * FROM COMPLAINT";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Complaint.class));
    }

    public List<Complaint> getComplaintByRollNo(int rollNo) {
        String sql = "SELECT * FROM COMPLAINT WHERE rollNo LIKE ?";
        String rollNoPattern = rollNo + "%";
        return jdbcTemplate.query(sql, new Object[]{rollNoPattern}, new BeanPropertyRowMapper<>(Complaint.class));
    }
    public List<Complaint> getParticularComplaint(int studentID)
    {
        String sql = "SELECT * FROM COMPLAINT WHERE rollNo = ?";
        return jdbcTemplate.query(sql,new Object[] {studentID},new BeanPropertyRowMapper<>(Complaint.class));
    }

//    public void deleteComplaint(int complaintID){
//        String sql = "delete from Complaint where complaint_id=?";
//        jdbcTemplate.update(sql,complaintID);
//    }

    public void updateComplaintStatus(int complaintId) {
        String sql = "UPDATE Complaint SET status = ? WHERE complaint_id = ?";
        jdbcTemplate.update(sql, "Completed", complaintId);
    }

}
