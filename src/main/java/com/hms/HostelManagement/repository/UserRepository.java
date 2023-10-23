package com.hms.HostelManagement.repository;

import com.hms.HostelManagement.model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate template;


    public void createUser(@NotNull User user) {
        String sql = "INSERT INTO User(username, password, role) VALUES (?, ?, ?)";
        template.update(sql, user.getUsername(), user.getPassword(), user.getRole());
    }



    public User getUser(String username) {
        String sql = "SELECT * FROM user WHERE username = ?";
        return template.queryForObject(sql, new Object[]{username},new BeanPropertyRowMapper<>(User.class));
    }

    public void update(User user) {
        String sql = "UPDATE user SET password = ? WHERE username = ?";
        template.update(sql, user.getPassword(), user.getUsername(),user.getRole());
    }

    public List<User> getUserWithRoles(String role) {
        String sql = "SELECT * FROM user WHERE role = ?";
        return template.query(sql, new Object[]{role}, new BeanPropertyRowMapper<>(User.class));
    }

}
