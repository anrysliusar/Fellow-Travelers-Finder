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

@Controller
@RequestMapping("/trips")
public class TripController {
    private final TripService tripService;
    private final UserService userService;
    private final RouteService routeService;

    @Autowired
    public TripController(TripService tripService, UserService userService, RouteService routeService) {
        this.tripService = tripService;
        this.userService = userService;
        this.routeService = routeService;
    }

    @GetMapping()
    public String trips(Model model){
        var all = tripService.getAll();
        model.addAttribute("trips", all);
        model.addAttribute("formInput", new TripForm());

        return "index";
    }

    @GetMapping("/all")
    public String showTrips(Model model) {
        model.addAttribute("trips", tripService.getAll());
        return "trips/all";
    }

    @GetMapping("/{id}")
    public String showTrip(@PathVariable int id, Model model) {

        model.addAttribute("chosenTrip", tripService.getById(id));
        return "redirect:/trips";
    }

    @GetMapping("/new")
    public String newTrips(Model model) {
        model.addAttribute("formInput", new TripForm());
        return "trips/formNew";
    }

    @GetMapping("/remove")
    public String removeTrips(Model model) {
        model.addAttribute("trips", tripService.getAll());
        return "trips/formRemove";
    }

    @PostMapping()
    public String createTrip(@ModelAttribute("formInput") TripForm tripForm) {
        var userWrap = userService.getByUsername(tripForm.getUsername());
        if(userWrap.isPresent()) {
            var route = new Route();
            route.setDeparturePoint(tripForm.getDeparturePoint());
            route.setArrivalPoint(tripForm.getArrivalPoint());

            var trip = new Trip();
            trip.setDepartureDate(tripForm.getDateDep());
            trip.setArrivalDate(tripForm.getDateArr());
            trip.setInitiator(userWrap.get());
            Trip saveTrip = tripService.save(trip);

            routeService.addRouteToTrip(route, saveTrip.getId());
        }
        return "redirect:/trips";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        tripService.deleteById(id);
        return "redirect:/trips";
    }
}
