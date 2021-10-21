package com.kpi.fellowtravelersfinder.service;

import com.kpi.fellowtravelersfinder.model.Route;
import com.kpi.fellowtravelersfinder.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();
    Optional<User> getById(int id);
    void save(User trip);
    void update(User trip, int id);
    void deleteById(int id);
}
