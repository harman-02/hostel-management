package com.hms.HostelManagement.repository;
import com.hms.HostelManagement.model.HostelRegistration;
import com.hms.HostelManagement.model.StudentUserMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class HostelRegistrationRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public HostelRegistration getHostelIDFromHostelRegistrationId(Integer HostelRegistrationId){
        String sql="select * from Hostel_registration where hostel_registration_id=?";
        return jdbcTemplate.queryForObject(sql,new Object[]{HostelRegistrationId}, new BeanPropertyRowMapper<>(HostelRegistration.class));
    }
    public HostelRegistration getSessionFromHostelRegistrationId(Integer HostelRegistrationId){
        String sql="select * from Hostel_registration where hostel_registration_id=?";
        return jdbcTemplate.queryForObject(sql,new Object[]{HostelRegistrationId}, new BeanPropertyRowMapper<>(HostelRegistration.class));
    }

}
