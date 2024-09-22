package com.crio.CoderHack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.crio.CoderHack.dto.User;
import com.crio.CoderHack.exceptions.UserNotFoundException;

@Service
public interface UserService {
    User registerUser(User user);
    
    User updateUserScore(String userId, int score) throws UserNotFoundException;

    Optional<User> getUser(String userId);

    List<User> getAllUsers();

    void deregisterUser(String userId) throws UserNotFoundException;
}
