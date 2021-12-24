package com.kpi.fellowtravelersfinder.service;

import com.kpi.fellowtravelersfinder.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getById(int id);
    User getByUsername(String username);
    void save(User user);
    boolean IsAuthenticatedUserHasRole(String role);
    boolean update(User user, int id);
}
