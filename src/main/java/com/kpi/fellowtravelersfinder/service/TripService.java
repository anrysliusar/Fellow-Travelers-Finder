package com.kpi.fellowtravelersfinder.service;

import com.kpi.fellowtravelersfinder.model.Trip;

import java.util.List;
import java.util.Optional;

public interface TripService {
    List<Trip> getAll();
    Optional<Trip> getById(int id);
    boolean save(Trip trip);
    boolean update(Trip trip, int id);
    void deleteById(int id);

}
