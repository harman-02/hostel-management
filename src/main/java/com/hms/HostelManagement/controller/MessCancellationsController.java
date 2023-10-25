package com.hms.HostelManagement.controller;
import com.hms.HostelManagement.model.*;
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
import java.util.List;
@Controller
public class MessCancellationsController extends BaseController {
    @Autowired
    private MessCancellationsService messCancellationsService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
    @GetMapping("/mess")
    public String allCancellations(Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, httpSession);
        model.addAttribute("cancellation", messCancellationsService.getAll());
        return "messCancellations/allMessCancellations";
    }
    @GetMapping("/mess/add")
    public String addMess(Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        System.out.println(model);
        addDefaultAttributes(model, session);

        MessCancellations mess = new MessCancellations();
       HostelRegistration hostelRegistration = new HostelRegistration();
        model.addAttribute("mess", mess);
        model.addAttribute("hostel", hostelRegistration);
        return "messCancellations/addMessCancellations";
    }
    @GetMapping("/mess/checkBalance")
    public String checkBalance(Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        System.out.println(model);
        addDefaultAttributes(model, session);

        MessCancellations mess = new MessCancellations();
        model.addAttribute("mess", mess);
        return "messCancellations/checkMessBalance";
    }
    @PostMapping("/myBalance")
    public String myBalance(@ModelAttribute("mess") MessCancellations m, @RequestParam("year") Integer year, Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, httpSession);
//        System.out.println(model);
        List<MessCancellations> mi = messCancellationsService.balanceByRollNoAndSession(m.getRollNo(), year);
        System.out.println(model);
        model.addAttribute("mess", mi);
        return "messCancellations/myBalance";
    }
    @PostMapping("/mess")
    public String PostAddMess( MessCancellations m,HostelRegistration h, Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, session);
        Date utildate = m.getDate();
        java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());
        m.setDate(sqlDate);
        messCancellationsService.createMessCancellation(m,h);
        return "redirect:/mess";
    }
    @GetMapping("/mess/update/{id}")
    public String updateMess(@PathVariable(value = "id") Integer id, Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, httpSession);
        MessCancellations messCancellations = messCancellationsService.getById(id);
        model.addAttribute("cancellation", messCancellations);
        return "messCancellations/updateMessCancellation";
    }

    @GetMapping("/mess/filterByHostel")
    public String filterMess(Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }

        addDefaultAttributes(model, httpSession);
        MessCancellations mess = new MessCancellations();
        model.addAttribute("filter", mess);
        return "messCancellations/filterMessCancellation";
    }

    @GetMapping("/mess/filterBySession")
    public String filterSession(Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, httpSession);
        MessCancellations mess = new MessCancellations();
        model.addAttribute("filter", mess);
        return "messCancellations/SessionMessCancellation";
    }

    @GetMapping("/mess/filterBySessionAndHostel")
    public String filterSessionandHostel(Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, httpSession);
        MessCancellations mess = new MessCancellations();
        model.addAttribute("sessionsandhostel", mess);
        return "messCancellations/SessionAndHostelMessCancellation";
    }

    @GetMapping("/mess/filterByRollNo")
    public String filterByRollNo(Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, httpSession);
        model.addAttribute("filter", new MessCancellations());
        return "messCancellations/RollNoMessCancellation";
    }

    @GetMapping("/mess/filterByDate")
    public String filterByDate(Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, httpSession);
        model.addAttribute("filter", new RangeDateModel());
        return "messCancellations/DateMessCancellation";
    }
    @GetMapping("/mess/filterByRollNoAndSession")
    public String filterByRollNoAndSession(Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        addDefaultAttributes(model,httpSession);
        model.addAttribute("filter", new SessionAndRollNo());
        return "messCancellations/RollNoAndSessionMessCancellation";
    }
    @PostMapping("/update")
    public String PostUpdateMess(@ModelAttribute("cancellation") MessCancellations m, Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, httpSession);
        Date utildate = m.getDate();
        java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());
        m.setDate(sqlDate);
        messCancellationsService.updateMessCancellation(m);
        return "redirect:/mess";
    }

    @PostMapping("/filterByHostel")
    public String PostFilterMess(@ModelAttribute("filter") MessCancellations m, Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, httpSession);
        List<MessCancellations> mi = messCancellationsService.filterById(m.getHostelRegistrationId());
        for (MessCancellations mo : mi) {
            Date utildate = mo.getDate();
            java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());
            mo.setDate(sqlDate);
        }
        model.addAttribute("filter", mi);
        return "messCancellations/allHostelFilter";
    }
    @PostMapping("/mess/postFilterByRollNo")
    public String PostFilterByRollNo(@ModelAttribute("filter") MessCancellations messCancellations, Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, httpSession);
        List<MessCancellations> mi = messCancellationsService.filterByRollNo(messCancellations.getRollNo());
        for (MessCancellations mo : mi) {
            Date utildate = mo.getDate();
            java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());
            mo.setDate(sqlDate);
        }
        model.addAttribute("filter", mi);
        return "messCancellations/allRollNoFilter";
    }

    @PostMapping("/mess/postFilterByDate")
    public String PostFilterByDate(@ModelAttribute("filter") RangeDateModel rangeDateModel, Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, httpSession);
        List<MessCancellations> mi = messCancellationsService.filterByDate(rangeDateModel.getStart(), rangeDateModel.getEnd());
        for (MessCancellations mo : mi) {
            Date utildate = mo.getDate();
            java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());
            mo.setDate(sqlDate);
        }
        model.addAttribute("filter", mi);
        return "messCancellations/allRollNoFilter";
    }

    @PostMapping("/mess/postFilterByRollNoAndSession")
    public String PostFilterByDate(@ModelAttribute("filter") SessionAndRollNo sessionAndRollNo, Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, httpSession);
        List<MessCancellations> mi = messCancellationsService.filterByRollNoAndSession(sessionAndRollNo.getRollNo(), sessionAndRollNo.getSession());
        for (MessCancellations mo : mi) {
            Date utildate = mo.getDate();
            java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());
            mo.setDate(sqlDate);
        }
        model.addAttribute("filter", mi);
        return "messCancellations/allRollNoAndSessionFilter";
    }

    @PostMapping("/filterBySession")
    public String PostFilterMessSession(@ModelAttribute("filter") MessCancellations m, @RequestParam("year") int year, Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, httpSession);
        List<MessCancellations> mi = messCancellationsService.filterBySession((year));
        model.addAttribute("filter", mi);
        return "messCancellations/allSessionFilter";
    }
    @PostMapping("/filterBySessionAndHostel")
    public String PostFilterMessSessionAndHostel(@ModelAttribute("filter") MessCancellations m, @RequestParam("hostelregistrationid") Integer hostelregistrationid, @RequestParam("year") int year, Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, httpSession);
        List<MessCancellations> mi = messCancellationsService.filterBySessionAndHostel(hostelregistrationid, year);
        model.addAttribute("filter", mi);
        return "messCancellations/allHostelAndSessionFilter";
    }
    @GetMapping("mess/delete/{id}")
    public String deleteEntry(@PathVariable("id") Integer id, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        messCancellationsService.deleteMessCancellations(id);
        return "redirect:/mess";
    }
}
