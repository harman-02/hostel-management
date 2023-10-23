package com.hms.HostelManagement.service;

import com.hms.HostelManagement.model.User;
import com.hms.HostelManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface UserService {

    public User getUser(String username);

    public void changePassword(String username, User user);
    public List<User> getUserWithRoles(String role);
    public User makeRandomUser(String role);
    public void createUser(User user);
}



