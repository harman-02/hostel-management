package com.hms.HostelManagement.controller;

import com.hms.HostelManagement.model.MessCancellations;
import com.hms.HostelManagement.model.RangeDateModel;
import com.hms.HostelManagement.model.SessionAndRollNo;
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
        addDefaultAttributes(model, session);

        MessCancellations mess = new MessCancellations();

        model.addAttribute("messAdd", mess);
        return "messCancellations/addMessCancellations";

    }

    @PostMapping("/mess")
    public String PostAddMess(@ModelAttribute("messAdd") MessCancellations m, Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, session);
        Date utildate = m.getDate();
        java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());
        m.setDate(sqlDate);
        messCancellationsService.createMessCancellation(m);
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

    @GetMapping("/mess/filterhostel")
    public String filterMess(Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }

        addDefaultAttributes(model, httpSession);
        MessCancellations mess = new MessCancellations();
        model.addAttribute("filter", mess);
        return "messCancellations/filterMessCancellation";
    }

    @GetMapping("/mess/filtersession")
    public String filterSession(Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, httpSession);
        MessCancellations mess = new MessCancellations();
        model.addAttribute("session", mess);
        return "messCancellations/sessionMessCancellation";
    }

    @GetMapping("/mess/filtersessionandhostel")
    public String filterSessionandHostel(Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, httpSession);
        MessCancellations mess = new MessCancellations();
        model.addAttribute("sessionsandhostel", mess);
        return "messCancellations/sessionandhostelMessCancellation";
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
        addDefaultAttributes(model, httpSession);
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

    @PostMapping("/filterhostel")
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

    @PostMapping("/filtersession")
    public String PostFilterMessSession(@ModelAttribute("session") MessCancellations m, @RequestParam("year") int year, Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, httpSession);
        List<MessCancellations> mi = messCancellationsService.filterBysession((year));
        model.addAttribute("filter", mi);
        return "messCancellations/allHostelFiltersession";
    }

    @PostMapping("/filtersessionandhostel")
    public String PostFilterMessSessionandHostel(@ModelAttribute("sessionandhostel") MessCancellations m, @RequestParam("hostelregistrationid") Integer hostelregistrationid, @RequestParam("year") int year, Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, httpSession);
        List<MessCancellations> mi = messCancellationsService.filterBysessionandhostel(hostelregistrationid, year);
        model.addAttribute("filter", mi);
        return "messCancellations/allHostelFiltersessionandhostel";
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
