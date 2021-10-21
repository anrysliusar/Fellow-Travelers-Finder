package com.kpi.fellowtravelersfinder.service;

import com.kpi.fellowtravelersfinder.model.Route;

import java.util.List;
import java.util.Optional;

public interface RouteService {
    List<Route> getAll();
    Optional<Route> getById(int id);
    boolean save(Route route);
    boolean update(Route route, int id);
    void deleteById(int id);
    boolean addRouteToTrip(Route route, int tripId);
}
