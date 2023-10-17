package com.hms.HostelManagement.service;

import com.hms.HostelManagement.model.User;
import com.hms.HostelManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private StudentRepository students;

    public User getUser(String username){
        return userRepository.getUser(username);
    }

    public void changePassword(String username, User user) {
        user.setUsername(username);
        userRepository.update(user);
    }
}
