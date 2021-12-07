package com.kpi.fellowtravelersfinder.controller;

import com.kpi.fellowtravelersfinder.dto.TripForm;
import com.kpi.fellowtravelersfinder.service.TripService;
import com.kpi.fellowtravelersfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    private final UserService userService;
    private final TripService tripService;

    @Autowired
    public LoginController(UserService userService, TripService tripService) {
        this.userService = userService;
        this.tripService = tripService;
    }

    @GetMapping()
    public String start(Model model){
        boolean hasRole = userService.IsAuthenticatedUserHasRole("DRIVER");
        model.addAttribute("trips", tripService.getAll());
        model.addAttribute("formInput", new TripForm());
        model.addAttribute("canEdit", hasRole);
        return "index";
    }

    @GetMapping("/driver")//to redirect on admin page
    public String driver(Model model) {
        return "redirect:/";
    }
    @GetMapping("/user")//to redirect on user page
    public String user(Model model) {
        return "redirect:/";
    }
}
