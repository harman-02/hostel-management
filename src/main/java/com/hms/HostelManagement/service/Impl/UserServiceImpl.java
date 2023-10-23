package com.hms.HostelManagement.service.Impl;

import com.hms.HostelManagement.model.User;
import com.hms.HostelManagement.repository.UserRepository;
import com.hms.HostelManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(String username) {
        return userRepository.getUser(username);
    }

    @Override
    public void createUser(User user) {
        userRepository.createUser(user);
    }

    @Override
    public void changePassword(String username, User user) {
        user.setUsername(username);
        userRepository.update(user);
    }

    @Override
    public List<User> getUserWithRoles(String role) {
        return userRepository.getUserWithRoles(role);
    }

    @Override
    public User makeRandomUser(String role){
        User user=new User();
        user.setUsername("user"+(int)(Math.random()*100000));
        user.setPassword("pass"+(int)(Math.random()*100000));
        user.setRole(role);
        userRepository.createUser(user);
        return user;
    }
}
