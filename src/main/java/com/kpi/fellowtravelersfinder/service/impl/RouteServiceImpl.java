package com.kpi.fellowtravelersfinder.service.impl;

import com.kpi.fellowtravelersfinder.model.Route;
import com.kpi.fellowtravelersfinder.model.Trip;
import com.kpi.fellowtravelersfinder.repository.RouteRepository;
import com.kpi.fellowtravelersfinder.repository.TripRepository;
import com.kpi.fellowtravelersfinder.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final TripRepository tripRepository;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository, TripRepository tripRepository) {
        this.routeRepository = routeRepository;
        this.tripRepository = tripRepository;
    }

    @Override
    public List<Route> getAll() {
        return routeRepository.findAll();
    }

    @Override
    public Optional<Route> getById(int id) {
        return routeRepository.findById(id);
    }

    @Override
    public boolean save(Route route) {
        if(checkIfRouteExists(route)) return false;
        routeRepository.save(route);
        return true;
    }

    @Override
    public boolean update(Route route, int id) {
        Optional<Route> update = routeRepository.update(route);
        return update.isPresent();
    }

    @Override
    public void deleteById(int id) {
        routeRepository.delete(id);
    }

    @Override
    public boolean addRouteToTrip(Route route, int tripId) {
        Optional<Trip> trip = tripRepository.findById(tripId);
        if(trip.isEmpty()) return false;

        Route routeExists = getIfRouteExists(route);
        if(routeExists != null) {
            trip.ifPresent(t -> t.setRoute(routeExists));
            tripRepository.save(trip.get());
            return true;
        }
        trip.ifPresent(t -> t.setRoute(route));
        tripRepository.save(trip.get());
        return true;
    }

    private boolean checkIfRouteExists(Route route){
        List<Route> all = getAll();
        for(var routeToCompare : all)
            if(routeToCompare.getArrivalPoint().equals(route.getArrivalPoint()) &&
                    routeToCompare.getDeparturePoint().equals(route.getDeparturePoint()))
                return true;
        return false;
    }

    private Route getIfRouteExists(Route route){
        List<Route> all = getAll();
        for(var routeToCompare : all)
            if(routeToCompare.getArrivalPoint().equals(route.getArrivalPoint()) &&
                    routeToCompare.getDeparturePoint().equals(route.getDeparturePoint()))
                return routeToCompare;
        return null;
    }

}
