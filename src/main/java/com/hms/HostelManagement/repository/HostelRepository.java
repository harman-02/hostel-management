package com.hms.HostelManagement.repository;

import com.hms.HostelManagement.model.Hostel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HostelRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    RowMapper<Hostel> rowMapper = (rs, rowNum) -> {
        Hostel hostel = new Hostel();
        hostel.setHostelName(rs.getString("hostel_name"));
        hostel.setHostelAddress(rs.getString("hostel_address"));
        return hostel;
    };
    public void createHostel(Hostel hostel){
        String sql = "insert into Hostel(hostel_name, hostel_address) values (?, ?)";
        jdbcTemplate.update(sql, hostel.getHostelName(), hostel.getHostelAddress());
    }
    public List<Hostel> getAllHostels(){
        String sql = "select * from Hostel";
        return jdbcTemplate.query(sql, rowMapper);
    }
    public void deleteHostel(int HostelId){
            String sql = "delete from Hostel where Hostel_Id=?";
            jdbcTemplate.update(sql,HostelId);
    }
    public Optional<Hostel> getHostelById(Integer id){
        String sql = "select * from hostel where hostel_id=?";
        Hostel hostel = null;
        try {
            hostel = jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
        }catch(DataAccessException ex) {
            System.out.println("hostel not found for id" + id);
            // as a response
        }
        return Optional.ofNullable(hostel);
    }
    public void updateHostel(Hostel hostel){
        String sql = "UPDATE Hostel SET Hostel_Name=?,Hostel_Address =? WHERE Hostel_Id = ?";
        jdbcTemplate.update(sql,hostel.getHostelName(),hostel.getHostelAddress(),hostel.getHostelId());
    }
}







