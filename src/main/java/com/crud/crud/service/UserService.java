package com.crud.crud.service;

import com.crud.crud.model.Users;
import com.crud.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public Users createUser(Users users) {
        return userRepository.save(users);
    }

    public Users updateUser(Long userId, Users userDetails) {
        Users users = userRepository.findById(userId).orElse(null);
        if(users != null) {
            users.setFistName(userDetails.getFistName());
            users.setLastName(userDetails.getLastName());

            return userRepository.save(users);
        }
        return null;
    }

    public boolean deleteUser(Long userId) {
        Users users = userRepository.findById(userId).orElse(null);
        if (users != null) {
            userRepository.delete(users);
            return true;
        }
        return false;
    }
}
