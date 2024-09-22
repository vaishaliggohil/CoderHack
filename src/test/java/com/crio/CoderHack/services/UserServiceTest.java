package com.crio.CoderHack.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.crio.CoderHack.dto.User;
import com.crio.CoderHack.exceptions.UserNotFoundException;
import com.crio.CoderHack.repositories.UserRepository;

@SpringBootTest
public class UserServiceTest {

    @MockBean 
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;
    
    
    @Test
    public void testUserRegistration() {
        User user = new User();
      
        user.setUserId("1");
        user.setUsername("Vaishali");

        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        user = userService.registerUser(user);
        assertNotNull(user.getUserId());
        assertEquals(0, user.getScore());
    }
    
    @Test
    public void testScoreUpdateAndBadges() throws UserNotFoundException {
        User user = new User(); 
        user.setUserId("1");
        user.setUsername("User1");
        user.setScore(50);
        System.out.println("Score before update: " + user.getScore());
      
        when(userRepository.findById("1")).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        
        User updatedUser = userService.updateUserScore("1", 80);
        System.out.println("Score after update: " + updatedUser.getScore());
        assertNotNull(updatedUser);
        assertEquals(80, updatedUser.getScore());
        
        verify(userRepository, times(1)).findById("1");
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetAllUsers() {
        
        User user1 = new User(); 
        user1.setUserId("1");
        user1.setUsername("User1");
        user1.setScore(50);

        User user2 = new User();
        user2.setUserId("2");
        user2.setUsername("User2");
        user2.setScore(75);

        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findAllByOrderByScoreDesc()).thenReturn(users);
        
        List<User> userList = userService.getAllUsers();
        assertEquals(2, userList.size());
        assertEquals("User1", userList.get(0).getUsername());
        assertEquals("User2", userList.get(1).getUsername());
        verify(userRepository, times(1)).findAllByOrderByScoreDesc();
    }

    @Test
    public void testDeleteUser() throws UserNotFoundException {
        doNothing().when(userRepository).deleteById("1");
        userService.deregisterUser("1");
        verify(userRepository, times(1)).deleteById("1");
    }
}
