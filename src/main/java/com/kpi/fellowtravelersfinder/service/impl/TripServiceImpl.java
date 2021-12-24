package com.kpi.fellowtravelersfinder.service.impl;

import com.kpi.fellowtravelersfinder.dto.TripDto;
import com.kpi.fellowtravelersfinder.model.Pageable;
import com.kpi.fellowtravelersfinder.model.Route;
import com.kpi.fellowtravelersfinder.model.Trip;

import com.kpi.fellowtravelersfinder.repository.TripRepository;
import com.kpi.fellowtravelersfinder.service.RouteService;
import com.kpi.fellowtravelersfinder.service.TripService;
import com.kpi.fellowtravelersfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;
    private final UserService userService;
    private final RouteService routeService;


    @Autowired
    public TripServiceImpl(TripRepository tripRepository, UserService userService, RouteService routeService) {
        this.tripRepository = tripRepository;
        this.userService = userService;
        this.routeService = routeService;
    }

    @Override
    public List<Trip> getAll() {
        return tripRepository.findAll();
    }

    @Override
    public Trip getById(int id) {
        Optional<Trip> byId = tripRepository.findById(id);
        Trip trip = null;
        if (byId.isPresent()){
            trip = byId.get();
        }
        return trip;
    }

    @Override
    public Trip save(Trip trip) {
        if (trip != null){
            return tripRepository.save(trip);
        }
        return null;
    }

    @Override
    public boolean update(Trip trip, int id) {
        Optional<Trip> update = tripRepository.update(trip);
        return update.isPresent();
    }

    @Override
    public void deleteById(int id) {
        tripRepository.delete(id);
    }

    public Trip saveTripWithRoute(TripDto trip){
        var userWrap = userService.getByUsername(trip.getUsername());

        var route = new Route();
        route.setDeparturePoint(trip.getDeparturePoint());
        route.setArrivalPoint(trip.getArrivalPoint());
        var tripToSave = new Trip();
        tripToSave.setDepartureDate(trip.getDateDep());
        tripToSave.setArrivalDate(trip.getDateArr());
        tripToSave.setInitiator(userWrap);
        Trip saveTrip = save(tripToSave);
        routeService.addRouteToTrip(route, saveTrip.getId());
        return saveTrip;
    }

    public List<Trip> findAllByRoute(String departurePoint, String arrivalPoint) {
        return getAll().stream()
                .filter(trip ->
                            trip.getRoute().getDeparturePoint().equals(departurePoint) &&
                                trip.getRoute().getArrivalPoint().equals(arrivalPoint))
                .collect(Collectors.toList());
    }

    public List<Trip> findAllByDepartureDate(Date departureDate){
        return getAll().stream()
                .filter(trip -> trip
                        .getDepartureDate()
                        .after(departureDate))
                .collect(Collectors.toList());
    }

    public List<Trip> findAllByArrivalDate(Date arrivalDate){
        return getAll().stream()
                .filter(trip -> trip
                        .getDepartureDate()
                        .before(arrivalDate))
                .collect(Collectors.toList());
    }

    public List<Trip> findAllByRoute(String departurePoint, String arrivalPoint, Pageable pageable) {
        return getAll().stream()
                .filter(trip ->
                        trip.getRoute().getDeparturePoint().equals(departurePoint) &&
                                trip.getRoute().getArrivalPoint().equals(arrivalPoint))
                .skip(pageable.getOffset())
                .limit(pageable.getItemsCount())
                .collect(Collectors.toList());
    }
}
