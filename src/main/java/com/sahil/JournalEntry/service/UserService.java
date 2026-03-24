package com.sahil.JournalEntry.service;

import com.sahil.JournalEntry.Repository.UserRepository;
import com.sahil.JournalEntry.model.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username);
    }

    public User createNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER"));
        return userRepository.save(user);
    }

    public User createNewAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("ADMIN"));
        return userRepository.save(user);
    }

    public boolean saveUser(User user){
       userRepository.save(user);
       return true;
    }

    public User updateUser(String username, User newUser){
        User oldUser = userRepository.findByUsername(username);
        if (oldUser!=null){
            oldUser.setUsername(newUser.getUsername());
            oldUser.setPassword(newUser.getPassword());
            return createNewUser(oldUser);
        }else{
        return null;
        }
    }
}
