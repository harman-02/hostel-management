package com.hms.HostelManagement.repository;

import com.hms.HostelManagement.model.HostelRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HostelRegistrationRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void addRegistration(HostelRegistration hostelRegistration){
        String sql="insert into Hostel_registration(hostel_id,session,hostel_warden_id) values(?,?,?)";
        jdbcTemplate.update(sql,hostelRegistration.getHostelId(),hostelRegistration.getSession(),hostelRegistration.getHostelWardenId());
    }
}
