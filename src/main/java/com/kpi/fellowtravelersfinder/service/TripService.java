package com.kpi.fellowtravelersfinder.service;

import com.kpi.fellowtravelersfinder.dto.TripDto;
import com.kpi.fellowtravelersfinder.model.Pageable;
import com.kpi.fellowtravelersfinder.model.Trip;

import java.util.List;
import java.util.Optional;

public interface TripService {
    List<Trip> getAll();
    Trip getById(int id);
    Trip save(Trip trip);
    Trip saveTripWithRoute(TripDto trip);
    boolean update(Trip trip, int id);
    void deleteById(int id);
    List<Trip> findAllByRoute(String departurePoint, String arrivalPoint, Pageable pageable);

}
