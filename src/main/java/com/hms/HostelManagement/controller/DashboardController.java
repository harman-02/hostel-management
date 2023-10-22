package com.hms.HostelManagement.controller;

import com.hms.HostelManagement.model.Hostel;
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
    private ToastService toastService;

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
    public String addHostel(Model model, HttpSession session){
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, session);

        Hostel hostel = new Hostel();

        model.addAttribute("hostel", hostel);

        return "dashboard/addHostel";

    }

    @PostMapping("/hostels")
    public String PostAddHostel(@ModelAttribute("hostel") Hostel h, Model model,HttpSession session){
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        addDefaultAttributes(model,session);
        hostelservice.createHostel(h);
        return "redirect:/hostels";
    }


}
