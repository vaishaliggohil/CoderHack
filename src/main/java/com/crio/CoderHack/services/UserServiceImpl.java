package com.crio.CoderHack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.CoderHack.dto.Badge;
import com.crio.CoderHack.dto.User;
import com.crio.CoderHack.exceptions.UserNotFoundException;
import com.crio.CoderHack.repositories.UserRepository;


@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepo;

    @Override
    public User registerUser(User user) {
        if(user == null) {
            throw new IllegalArgumentException("User must not be null");
        }
       user.setScore(Badge.DEFAULT_CODE);
       user.setBadges(new ArrayList<>());
       return userRepo.save(user);
    }

    @Override
    public User updateUserScore(String userId, int score) throws UserNotFoundException {
        
        Optional<User> userOp = userRepo.findById(userId);
        if(!userOp.isPresent()) {
            throw new UserNotFoundException("User Not Found!");
        }
        User user = userOp.get();
        user.setScore(score);
        updateBadges(user);
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUser(String userId) {
        return userRepo.findById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepo.findAllByOrderByScoreDesc();
        return (users != null) ? users : new ArrayList<>();
    }

    @Override
    public void deregisterUser(String userId) throws UserNotFoundException {
       userRepo.deleteById(userId);
    }

    private void updateBadges(User user) {

        int score = user.getScore();
        List<String> badges = new ArrayList<>();

        if(score >= 1 && score < 30) {
            badges.add(Badge.CODE_NINJA);

        }
        if(score >= 30 && score < 60) {
            badges.add(Badge.CODE_NINJA);
            badges.add(Badge.CODE_CHAMP);
        }

        if(score >= 60 && score <= 100) {
            badges.add(Badge.CODE_NINJA);
            badges.add(Badge.CODE_CHAMP);
            badges.add(Badge.CODE_MASTER);
        }
        if(badges.size() <= 3) {
            user.setBadges(badges);
        }
    }

}
