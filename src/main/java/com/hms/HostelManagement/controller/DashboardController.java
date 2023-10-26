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
public class DashboardController extends BaseController {

    @Autowired
    private ToastService toastService;

    @Autowired
    private StudentService studentService;


    @Autowired
    private UserService userService;

    @Autowired
    private HostelService hostelservice;

    @Autowired
    private SessionService sessionService;
    @Autowired
    private HostelRegistrationService hostelRegistrationService;

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
            return "redirect:/login";
        }


        addDefaultAttributes(model, session);

        if(getRoleInSession(session).equals("student"))
        {
            model.addAttribute("thisStudent",studentUserMappingService.getStudentFromUsername(getUserInSession(session).getUsername()));
        }

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
        return "redirect:/hostels";
    }

    @GetMapping("/sessions")
    public String allSession(Model model,HttpSession session){
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        addDefaultAttributes(model,session);


        List<Session> st=sessionService.getAllSession();
        for(Session s:st)
            System.out.println(s.getSessionName()+" "+s.getStartDate());

        model.addAttribute("sessions",sessionService.getAllSession());

        return "dashboard/allSessions";
    }

    @GetMapping("/sessions/add")
    public String addSession(Model model,HttpSession session){
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        addDefaultAttributes(model,session);

        Session newSession = new Session();
        model.addAttribute("newSession",newSession);

        return "dashboard/addSession";
    }

    @PostMapping("/sessions")
    public String postaddSession(@ModelAttribute("newsession") Session s,Model model,HttpSession session){
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        sessionService.createSession(s);
        return "redirect:/sessions";
    }




    @GetMapping("/registeredHostels")
    public String RegisteredHostels(Model model,HttpSession session){
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }

        List<HostelRegistration>hr=hostelRegistrationService.getAllRegisteredHostel();

        List<HelperHostelSessionName> registeredHostels = new ArrayList<HelperHostelSessionName>();

        for(HostelRegistration s:hr)
        {
            HelperHostelSessionName curr=new HelperHostelSessionName();
            curr.setHostelName(hostelservice.getHostelFromId(s.getHostelId()).getHostelName());
            curr.setSessionName(sessionService.getSessionFromId(s.getSessionId()).getSessionName());
            curr.setUint(s.getRoomCount());
            registeredHostels.add(curr);
        }


        model.addAttribute("registeredhostels",registeredHostels);
        return "dashboard/registeredHostels";
    }
    @GetMapping("/registerHostelSession")
    public String RegisterHostelWithSession(Model model,HttpSession session){
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }

        HostelRegistration hostelRegistration=new HostelRegistration();
        model.addAttribute("hostelRegistration",hostelRegistration);

        model.addAttribute("hostels",hostelservice.getAllHostel());
        model.addAttribute("sessions",sessionService.getAllSession());
        return "dashboard/registerHostelSession";
    }


    @PostMapping("/registeredHostels")
    public String PostRegisteredHostels(@ModelAttribute("hostelRegistration") HostelRegistration hr,Model model,HttpSession session){
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }

        hostelRegistrationService.createHostelRegistration(hr);

        return "redirect:/registeredHostels";
    }





}
