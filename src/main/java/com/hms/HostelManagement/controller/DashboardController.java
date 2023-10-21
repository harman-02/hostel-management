package com.hms.HostelManagement.controller;

import com.hms.HostelManagement.model.Complaint;
import com.hms.HostelManagement.model.Hostel;
import com.hms.HostelManagement.model.User;
import com.hms.HostelManagement.repository.HostelRepository;
import com.hms.HostelManagement.repository.StudentUserMappingRepository;
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
import java.util.Objects;

@Controller
public class DashboardController extends BaseController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private HostelService hostelservice;

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private UserService userService;

    @Autowired
    private StudentUserMappingService studentUserMappingService;

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
    @GetMapping("/complaints")
    public String allcomplaint(Model model, HttpSession session, Integer keyword) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, session);

        if (keyword != null) {
            model.addAttribute("complaints", complaintService.getComplaintByRollNo(keyword));
        } else {
            model.addAttribute("complaints", complaintService.getAllComplaint());
        }

        return "dashboard/allComplaint";
    }

    @GetMapping("/mycomplaints")
    public String mycomplaint(Model model, HttpSession session) {
        if(!isAuthenticated(session))
            return "redirect:/";
        String username = authenticationService.getCurrentUser(session);
        User user= userService.getUser(username);
        String currRole = user.getRole();
        if(!Objects.equals(currRole, "student"))
            return "redirect:/dashboard";
        addDefaultAttributes(model, session);
        int rollNo = studentUserMappingService.getRollNofromUsername(username);
        model.addAttribute("complaints", complaintService.getParticularComplaint(rollNo));
        return "dashboard/myComplaints";
    }
    @PostMapping("/complaints")
    public String PostaddComplaint(@ModelAttribute("complaint") Complaint complaint, Model model, HttpSession session){
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        addDefaultAttributes(model,session);
        complaintService.createComplaint(complaint);
        return "redirect:/complaints";
    }

    @GetMapping("/complaints/add")
    public String addComplaint(Model model,HttpSession session){
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        addDefaultAttributes(model,session);
        Complaint complaint=new Complaint();
        model.addAttribute("complaint",complaint);
        return "dashboard/addComplaint";

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
        return "redirect:/hostels";
    }



}
