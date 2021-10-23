package com.kpi.fellowtravelersfinder.controller;

import com.kpi.fellowtravelersfinder.dto.TripForm;
import com.kpi.fellowtravelersfinder.model.Route;
import com.kpi.fellowtravelersfinder.model.Trip;
import com.kpi.fellowtravelersfinder.service.RouteService;
import com.kpi.fellowtravelersfinder.service.TripService;
import com.kpi.fellowtravelersfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/trips")
public class TripController {
    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping()
    public String trips(Model model){
        List<Trip> all = tripService.getAll();
        model.addAttribute("trips", all);
        return "index";
    }

}
