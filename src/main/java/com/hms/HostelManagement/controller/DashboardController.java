package com.hms.HostelManagement.controller;

import com.hms.HostelManagement.model.Hostel;
import com.hms.HostelManagement.model.HostelRegistration;
import com.hms.HostelManagement.model.Session;
import com.hms.HostelManagement.model.Student;
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
import java.util.Date;

@Controller
public class DashboardController extends BaseController {

    @Autowired
    private HostelRegistrationService hostelRegistrationService;

    @Autowired
    private ToastService toastService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private StudentService studentService;


    @Autowired
    private UserService userService;

    @Autowired
    private HostelService hostelservice;

    // Needed to automatically convert String date in form to Date object.
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }

        addDefaultAttributes(model, session);
        return "dashboard/index";
    }



    @GetMapping("/hostels")
    public String allHostel(Model model,HttpSession session){
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        addDefaultAttributes(model,session);

        model.addAttribute("hostels",hostelservice.getAllHostel());

        return "dashboard/allHostel";

    }

    // getmapping add hostel , temporaily pass new empty hostel object to form template

    @GetMapping("/hostels/add")
    public String addHostel(Model model,HttpSession session){
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        addDefaultAttributes(model,session);

        Hostel hostel=new Hostel();

        model.addAttribute("hostel",hostel);

        return "dashboard/addHostel";

    }

    @PostMapping("/hostels")
    public String PostaddHostel(@ModelAttribute("hostel") Hostel h, Model model,HttpSession session){
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        addDefaultAttributes(model,session);
        hostelservice.createHostel(h);
        return "/hostels";
    }

    @GetMapping("/session/add")
    public String addSession(@ModelAttribute("session")Session s,Model model,HttpSession session){
        if(!isAuthenticated(session) && !isAdmin(session)){
            return "redirect:/";
        }
        addDefaultAttributes(model,session);
        Session session1=new Session();
        model.addAttribute("session",session1);
        return "dashboard/addSession";
    }

    @PostMapping("/session/add")
    public String PostaddSession(@ModelAttribute("session")Session s,Model model,HttpSession session){
        if(!isAuthenticated(session) && !isAdmin(session)){
            return "redirect:/";
        }
        addDefaultAttributes(model,session);
        sessionService.createSession(s);
        System.out.println(sessionService.getAllSession());
        return "redirect:/";
    }
//    @GetMapping("/session")
//    public String allSession(Model model,HttpSession session){
//        if(!isAuthenticated(session) && !isAdmin(session)){
//            return "redirect:/";
//        }
//        addDefaultAttributes(model,session);
//        model.addAttribute("sessions",sessionService.getAllSession());
//        return "dashboard/allSession";
//    }

    @GetMapping("/hostel/register")
    public String registerHostel(Model model,HttpSession session){
        if(!isAuthenticated(session) && !isAdmin(session)){
            return "redirect:/";
        }
        addDefaultAttributes(model,session);
        model.addAttribute("sessions",sessionService.getAllSession());
        model.addAttribute("hostels",hostelservice.getAllHostel());
        HostelRegistration hostelRegistration=new HostelRegistration();
        model.addAttribute("hostelRegistration",hostelRegistration);
        model.addAttribute("wardens",userService.getUserWithRoles("warden"));
        return "dashboard/registerHostel";
    }

    @PostMapping("/hostel/register")
    public String PostregisterHostel(@ModelAttribute("hostel")HostelRegistration hostelRegistration,Model model,HttpSession session){
        if(!isAuthenticated(session) && !isAdmin(session)){
            return "redirect:/";
        }
        hostelRegistrationService.registerHostel(hostelRegistration);
        return "redirect:/hostel/register";
    }

//    @GetMapping("/hostel/{id}")
//    public String getHostelById(@PathVariable("id") Integer id,Model model,HttpSession session){
//        if(!isAuthenticated(session) && !isAdmin(session)){
//            return "redirect:/";
//        }
//        addDefaultAttributes(model,session);
//
//        model.addAttribute("hostel",hostelservice.getHostelById(id));
//        return "dashboard/hostelDetail";
//    }

//    @GetMapping("session/{id}")
//    public String getSessionById(@PathVariable("id") Integer id,Model model,HttpSession session){
//        if(!isAuthenticated(session) && !isAdmin(session)){
//            return "redirect:/";
//        }
//        addDefaultAttributes(model,session);
//
//        model.addAttribute("session",sessionService.getSessionById(id));
//        return "dashboard/sessionDetail";
//    }

    @GetMapping("/student/register")
    public String registerStudent(Model model,HttpSession session){
        if(!isAuthenticated(session) && !isAdmin(session)){
            return "redirect:/";
        }
        addDefaultAttributes(model,session);
        Student student=new Student();
        model.addAttribute("student",student);
        return "dashboard/registerStudent";
    }
    @PostMapping("/student/register")
    public String PostregisterStudent(@ModelAttribute("student") Student s, Model model, HttpSession session){
        if(!isAuthenticated(session) && !isAdmin(session)){
            return "redirect:/";
        }
        System.out.println(s);
        addDefaultAttributes(model,session);
        s.setBalance(0);

        studentService.addStudent(s);
        return "redirect:/";
    }

//    @GetMapping("/student")
//    public String StudentDashBoard(Model model,HttpSession session){
//        if(!isAuthenticated(session) && !isAdmin(session)){
//            return "redirect:/";
//        }
//        addDefaultAttributes(model,session);
//        model.addAttribute("students",studentService.getAllStudent());
//        return "dashboard/allStudent";
//    }
//
//    @GetMapping String allStudent(Model model,HttpSession session){
//        if(!isAuthenticated(session) && !isAdmin(session)){
//            return "redirect:/";
//        }
//        addDefaultAttributes(model,session);
//        model.addAttribute("students",studentService.getAllStudent());
//        return "dashboard/allStudent";
//    }
//
//    @GetMapping("/student/{id}")
//    public String getStudentById(@PathVariable("id") Integer id,Model model,HttpSession session){
//        if(!isAuthenticated(session) && !isAdmin(session)){
//            return "redirect:/";
//        }
//        addDefaultAttributes(model,session);
//        model.addAttribute("student",studentService.getStudentById(id));
//        return "dashboard/studentDetail";
//    }
//    @GetMapping("/student/{id}/edit")
//    public String editStudentById(@PathVariable("id") Integer id,Model model,HttpSession session){
//        if(!isAuthenticated(session) && !isAdmin(session)){
//            return "redirect:/";
//        }
//        addDefaultAttributes(model,session);
//        model.addAttribute("student",studentService.getStudentById(id));
//        return "dashboard/editStudent";
//    }
//    @PostMapping("/student/{id}/edit")
//    public String PosteditStudentById(@PathVariable("id") Integer id,@ModelAttribute("student") Student s,Model model,HttpSession session){
//        if(!isAuthenticated(session) && !isAdmin(session)){
//            return "redirect:/";
//        }
//        addDefaultAttributes(model,session);
//        studentService.updateStudent(s);
//        return "redirect:/student";
//    }
//    @DeleteMapping("/student/{id}")
//    public String deleteStudentById(@PathVariable("id") Integer id,Model model,HttpSession session){
//        if(!isAuthenticated(session) && !isAdmin(session)){
//            return "redirect:/";
//        }
//        addDefaultAttributes(model,session);
//        studentService.deleteStudent(id);
//        return "redirect:/student";
//    }
//
    @GetMapping("student/registerRoom")
    public String registerRoom(Model model,HttpSession session){
        if(!isAuthenticated(session) && !isAdmin(session)){
            return "redirect:/";
        }
        addDefaultAttributes(model,session);

//        model.addAttribute("students",studentService.getAllStudent());
//        model.addAttribute("hostels",hostelservice.getAllHostel());
        return "dashboard/registerRoom";
    }
    @PostMapping("student/registerRoom")
    public String PostregisterRoom(@ModelAttribute("student") Student s,Model model,HttpSession session){
        if(!isAuthenticated(session) && !isAdmin(session)){
            return "redirect:/";
        }
        addDefaultAttributes(model,session);

//        studentService.createStudent(s);
        return "redirect:/";
    }




}
