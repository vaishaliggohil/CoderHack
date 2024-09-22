package com.crio.CoderHack.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crio.CoderHack.dto.User;
import com.crio.CoderHack.exceptions.UserNotFoundException;
import com.crio.CoderHack.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Retrieve a list of all registered users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Retrieve the details of a specific user
    @GetMapping("/users/{userId}")
    public Optional<User> getUser(@RequestParam String userId) throws UserNotFoundException {
        return userService.getUser(userId);
    }
    
    // Register a new user to the contest
    @PostMapping
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user, BindingResult bindingResult) throws UserNotFoundException{
        
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User newuser = userService.registerUser(user);
        return new ResponseEntity<>(newuser, HttpStatus.CREATED);
    }

    // Update the score of a specific user
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateScore(@PathVariable String userId, @RequestBody Map<String, Integer> updates) {
        Integer score = updates.get("score");

        if(score == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            User user = userService.updateUserScore(userId, score);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException u) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException i) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
       
    }

    // Deregister a specific user from the contest
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId){
        try {
            userService.deregisterUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (UserNotFoundException u) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
