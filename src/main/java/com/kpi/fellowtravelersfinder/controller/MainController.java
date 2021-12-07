package com.kpi.fellowtravelersfinder.controller;

import com.kpi.fellowtravelersfinder.dto.TripForm;
import com.kpi.fellowtravelersfinder.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private final TripService tripService;


    @Autowired
    public MainController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("trips", tripService.getAll());
        model.addAttribute("formInput", new TripForm());
        return "trips/add";
    }

    @GetMapping("/modify")
    public String modify(Model model){
        model.addAttribute("trips", tripService.getAll());
        return "trips/mod";
    }

    @GetMapping("/delete")
    public String delete(Model model){
        model.addAttribute("trips", tripService.getAll());
        return "trips/delete";
    }

    @GetMapping("/editable")
    public String setEditable(Model model){
        model.addAttribute("canEdit", 0);
        return "main/sidebar";
    }
}
