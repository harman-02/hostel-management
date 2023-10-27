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
import java.util.Objects;

@Controller
public class MessCancellationsController extends BaseController {
    @Autowired
    private MessCancellationsService messCancellationsService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserService userService;
    @Autowired
    private StudentUserMappingService studentUserMappingService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    @GetMapping("/mess")
    public String allCancellations(Model model, HttpSession httpSession, String keyword) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }

        addDefaultAttributes(model, httpSession);
        if (keyword == null) {
            model.addAttribute("cancellation", messCancellationsService.getAll());
        }
        else {
            model.addAttribute("cancellation", messCancellationsService.findByKeyword(keyword));
        }
        return "messCancellations/allMessCancellations";
    }

    @GetMapping("/mymess")
    public String myCancellations(Model model, HttpSession httpSession, String keyword) {
        if(!isAuthenticated(httpSession)){
            return "redirect:/";
        }
        String username = authenticationService.getCurrentUser(httpSession);
        User user = userService.getUser(username);
        String currRole = user.getRole();
        if (!Objects.equals(currRole, "student")) {
            return "redirect:/dashboard";
        }
        addDefaultAttributes(model, httpSession);
        int rollNo = studentUserMappingService.getRollNofromUsername(username);
        System.out.println(rollNo);
        if (keyword == null) {
            model.addAttribute("cancellation", messCancellationsService.filterByRollNo(rollNo));
        }
        else {
            model.addAttribute("cancellation", messCancellationsService.findByKeywordAndRollNo(keyword, rollNo));
        }
        return "messCancellations/myMessCancellations";
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
        List<MessCancellations> mi = messCancellationsService.balanceByRollNoAndSession(m.getRollNo(), year);
        System.out.println(model);
        model.addAttribute("mess", mi);
        return "messCancellations/myBalance";
    }

    @PostMapping("/mess")
    public String PostAddMess(MessCancellations m, HostelRegistration h, Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, session);
        Date utildate = m.getDate();
        java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());
        m.setDate(sqlDate);
        messCancellationsService.createMessCancellation(m, h);
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

    @GetMapping("/mess/filterByHostelAndSession")
    public String filterMess(Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }

        addDefaultAttributes(model, httpSession);
        model.addAttribute("filter", new AllMessCancellations());
        return "messCancellations/HostelAndSessionMessCancellation";
    }

    @GetMapping("/mess/filterBySession")
    public String filterSession(Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, httpSession);
        model.addAttribute("cancellation", new AllMessCancellations());
        return "messCancellations/SessionMessCancellation";
    }

    @GetMapping("/mess/filterByHostel")
    public String filterByHostel(Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, httpSession);
        model.addAttribute("cancellation", new AllMessCancellations());
        return "messCancellations/HostelMessCancellation";
    }

    @GetMapping("/mess/filterByRollNo")
    public String filterByRollNo(Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, httpSession);
        model.addAttribute("cancellation", new AllMessCancellations());
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

    @PostMapping("/filterByHostelAndSession")
    public String PostFilterMess(@ModelAttribute("filter") AllMessCancellations m, Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, httpSession);
        List<AllMessCancellations> mi = messCancellationsService.filterByHostelIdAndSessionId(m.getHostelId(), m.getSessionId());
        for (AllMessCancellations mo : mi) {
            Date utildate = mo.getDate();
            java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());
            mo.setDate(sqlDate);
        }
        model.addAttribute("cancellation", mi);
        return "messCancellations/allMessCancellations";
    }

    @PostMapping("/mess/postFilterByRollNo")
    public String PostFilterByRollNo(@ModelAttribute("cancellation") AllMessCancellations allMessCancellations, Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, httpSession);
        List<AllMessCancellations> mi = messCancellationsService.filterByRollNo(allMessCancellations.getStudentRollNo());
//        System.out.println(allMessCancellations.getStudentRollNo());
        for (AllMessCancellations mo : mi) {
            Date utildate = mo.getDate();
            java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());
            mo.setDate(sqlDate);
        }
        model.addAttribute("cancellation", mi);
        return "messCancellations/allMessCancellations";
    }

    @PostMapping("/mess/postFilterByDate")
    public String PostFilterByDate(@ModelAttribute("filter") RangeDateModel rangeDateModel, Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, httpSession);
        List<AllMessCancellations> mi = messCancellationsService.filterByDate(rangeDateModel.getStart(), rangeDateModel.getEnd());
        for (AllMessCancellations mo : mi) {
            Date utildate = mo.getDate();
            java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());
            mo.setDate(sqlDate);
        }
        model.addAttribute("cancellation", mi);
        return "messCancellations/allMessCancellations";
    }

    @PostMapping("/mess/postFilterByRollNoAndSession")
    public String PostFilterByDate(@ModelAttribute("filter") SessionAndRollNo sessionAndRollNo, Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, httpSession);
        List<AllMessCancellations> mi = messCancellationsService.filterByRollNoAndSession(sessionAndRollNo.getRollNo(), sessionAndRollNo.getSession());
        for (AllMessCancellations mo : mi) {
            Date utildate = mo.getDate();
            java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());
            mo.setDate(sqlDate);
        }
        model.addAttribute("cancellation", mi);
        return "messCancellations/allMessCancellations";
    }

    @PostMapping("/filterBySession")
    public String PostFilterMessSession(@ModelAttribute("cancellation") AllMessCancellations m, @RequestParam("year") int year, Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, httpSession);
        List<AllMessCancellations> mi = messCancellationsService.filterBySession(year);
        model.addAttribute("cancellation", mi);
        return "messCancellations/allMessCancellations";
    }

    @PostMapping("/filterByHostel")
    public String PostFilterMessSessionAndHostel(@ModelAttribute("cancellation") AllMessCancellations m, @RequestParam("hostelid") Integer hostelId, Model model, HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, httpSession);
        List<AllMessCancellations> mi = messCancellationsService.filterByHostel(hostelId);
        model.addAttribute("cancellation", mi);
        return "messCancellations/allMessCancellations";
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
