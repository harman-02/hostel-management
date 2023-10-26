package com.hms.HostelManagement.controller;

import com.hms.HostelManagement.model.User;
import com.hms.HostelManagement.service.AuthenticationService;
import com.hms.HostelManagement.service.StudentService;
import com.hms.HostelManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

abstract class BaseController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;


    public Boolean isAuthenticated(HttpSession session) {
        return authenticationService.isAuthenticated(session);
    }

    public void addDefaultAttributes(Model model, HttpSession session) {
        String currentUser = authenticationService.getCurrentUser(session);
        model.addAttribute("username", currentUser);
        if (currentUser != null) {

            model.addAttribute("userImageUrl", "https://ui-avatars.com/api/?name=" + currentUser);

            User user= userService.getUser(currentUser);
            model.addAttribute("user", user);
//
            String currRole=user.getRole();

            if (currRole.equals("student")) {
                model.addAttribute("student", studentService.getStudentByRollNo(currentUser));
            }
        }
    }
}
