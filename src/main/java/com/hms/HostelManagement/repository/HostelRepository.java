package com.hms.HostelManagement.repository;

import com.hms.HostelManagement.model.Hostel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HostelRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public void createHostel(Hostel hostel){
        String sql = "insert into Hostel(hostel_name, hostel_address) values (?, ?)";
        jdbcTemplate.update(sql, hostel.getHostelName(), hostel.getHostelAddress());
    }
    public List<Hostel> getAllHostels(){
        String sql = "select * from Hostel";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Hostel.class));
    }
    public void deleteHostel(int HostelId){
            String sql = "delete from Hostel where Hostel_Id=?";
            jdbcTemplate.update(sql,HostelId);
    }
    public void updateHostel( Hostel hostel){
        String sql = "UPDATE Hostel SET Hostel_Name=?,Hostel_Address =? WHERE Hostel_Id = ?";
        jdbcTemplate.update(
                sql,hostel.getHostelName(),hostel.getHostelAddress(),hostel.getHostelId());
    }
}







