package com.kpi.fellowtravelersfinder.service.impl;

import com.kpi.fellowtravelersfinder.model.User;
import com.kpi.fellowtravelersfinder.repository.UsersRepository;
import com.kpi.fellowtravelersfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;


    @Override
    public Optional<User> getById(int id) {
        return usersRepository.findById(id);
    }

    @Override
    public User getByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    public void save(User user) {
        usersRepository.save(user);
    }

    @Override
    public boolean IsAuthenticatedUserHasRole(String role) {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority
                            .getAuthority()
                            .equals("ROLE_"+ role));
    }

    @Override
    public boolean update(User user, int id) {
        return usersRepository.update(user).isPresent();
    }
}
