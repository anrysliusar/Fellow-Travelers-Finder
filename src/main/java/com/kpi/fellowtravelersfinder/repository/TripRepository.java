package com.kpi.fellowtravelersfinder.repository;

import com.kpi.fellowtravelersfinder.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
}
