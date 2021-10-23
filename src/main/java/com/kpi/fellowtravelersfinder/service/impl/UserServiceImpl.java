package com.kpi.fellowtravelersfinder.service.impl;

import com.kpi.fellowtravelersfinder.model.User;
import com.kpi.fellowtravelersfinder.repository.UserRepository;
import com.kpi.fellowtravelersfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public Optional<User> getById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean update(User user, int id) {
        if(userRepository.existsById(id)){
            user.setId(id);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
