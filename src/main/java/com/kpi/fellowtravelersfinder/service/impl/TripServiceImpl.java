package com.kpi.fellowtravelersfinder.service.impl;

import com.kpi.fellowtravelersfinder.model.Trip;
import com.kpi.fellowtravelersfinder.repository.TripRepository;
import com.kpi.fellowtravelersfinder.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;


    @Autowired
    public TripServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public List<Trip> getAll() {
        return tripRepository.findAll();
    }

    @Override
    public Optional<Trip> getById(int id) {
        return tripRepository.findById(id);
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
        if (!tripRepository.existsById(id)) {
            return false;
        }
        trip.setId(id);
        return save(trip) != null;
    }

    @Override
    public void deleteById(int id) {
        if(tripRepository.existsById(id)) tripRepository.deleteById(id);
    }

}
