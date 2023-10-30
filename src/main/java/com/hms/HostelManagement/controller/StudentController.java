package com.hms.HostelManagement.controller;

import com.hms.HostelManagement.model.*;
import com.hms.HostelManagement.repository.HostelRepository;
import com.hms.HostelManagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class StudentController extends BaseController{
    @Autowired
    private ToastService toastService;

    @Autowired
    private StudentService studentService;


    @Autowired
    private UserService userService;

    @Autowired
    private HostelService hostelservice;

    @Autowired
    private HostelRegistrationService hostelRegistrationService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private StudentUserMappingService studentUserMappingService;

    @GetMapping("/registerStudent")
    public String registerStudent(Model model,HttpSession session)
    {
        if(!isAuthenticated(session))
            return "redirect:/";
        addDefaultAttributes(model,session);

        String role=getRoleInSession(session);

        if(role.equals("admin"))
        {

        }
        else
            return "redirect:/";


         // studentusermapping , username password roll hostelregistrationid


        StudentUserMapping studentUserMapping=new StudentUserMapping();
        model.addAttribute("studentUserMapping",studentUserMapping);

        User newUser= new User();
        newUser.setRole("student");
        model.addAttribute("newUser",newUser);

        List<HelperHostelSessionName> registeredHostels = new ArrayList<HelperHostelSessionName>();

        List<HostelRegistration>hr=hostelRegistrationService.getAllRegisteredHostel();
        for(HostelRegistration s:hr)
        {
            HelperHostelSessionName curr=new HelperHostelSessionName();
            curr.setHostelName(hostelservice.getHostelFromId(s.getHostelId()).getHostelName());
            curr.setSessionName(sessionService.getSessionFromId(s.getSessionId()).getSessionName());
            curr.setUint(s.getHostelRegistrationId());
            registeredHostels.add(curr);
//            System.out.println(curr.getUint()+" "+ curr.getHostelName()+" "+curr.getSessionName());
        }


        model.addAttribute("registeredhostels",registeredHostels);


        return "dashboard/registerStudent";
    }
    @PostMapping("/registerStudent")
    public String PostregisterStudent(@ModelAttribute("newUser") User newUser,@ModelAttribute("studentUserMapping") StudentUserMapping s, Model model,HttpSession session){
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        String role=getRoleInSession(session);

        if(role.equals("admin"))
        {

        }
        else
            return "redirect:/";

        if(userService.checkUserNameExists(newUser.getUsername()))
        {
            // username already exists
        }
        else
        {
            // also have to check roll no present in hostel registration id of same session
            // check same roll  present in multiple hostels , check roll

            newUser.setRole("student");
            userService.createUser(newUser);

             if(studentService.checkRollNoExists(s.getRoll()))
            {
                // student already added in students table

            }
            else
            {

                Student newStudent = new Student();
                newStudent.setRoll(s.getRoll());
                newStudent.setBalance(0); // set default balance
                studentService.createStudent(newStudent);
            }
            s.setUsername(newUser.getUsername());
            studentUserMappingService.createStudentUserMapping(s);
        }

        return "redirect:/students";
    }


//    @GetMapping("/student/myProfile")
//    public String myProfile(Model model,HttpSession session){
//
//        if(getRoleInSession(session).equals("student"))
//        {
//            model.addAttribute("thisStudent",studentUserMappingService.getStudentFromUsername(getUserInSession(session).getUsername()));
//        }
//        addDefaultAttributes(model,session);
//        return "dashboard/myProfile";
//    }

    @GetMapping("/student/myProfile/{id}")
    public  String updateMyProfile(@PathVariable int id, Model model,HttpSession session){

        model.addAttribute("thisStudent",studentService.getStudentFromRoll(id));

        return "dashboard/updatemyProfile";
    }

    @PostMapping("/student/myProfile/{id}")
    public  String postupdateMyProfile(@PathVariable int id,@ModelAttribute("thisStudent") Student s, Model model,HttpSession session){

        System.out.println(s.getName()+" "+s.getEmail());
        studentService.updateStudentByRoll(s,id);

        return "redirect:/student/myProfile";
    }




}
