package com.sahil.JournalEntry.controllers;

import com.sahil.JournalEntry.model.JournalEntry;
import com.sahil.JournalEntry.model.User;
import com.sahil.JournalEntry.service.JournalEntryService;
import com.sahil.JournalEntry.service.QuoteService;
import com.sahil.JournalEntry.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuoteService quoteService;

    @GetMapping
    public ResponseEntity<String> getUserDetails(){
    User user= userService.getCurrentUser();
    return new ResponseEntity<>("Hi "+user.getUsername()+". Quote for Today is: "+quoteService.getQuotes().get(0).getQuote(),HttpStatus.OK);
    }

//    @GetMapping
//    public User getUserByUsername(){
//        return userService.getUserDetails();
//    }

    @PostMapping
    public boolean newUser(@RequestBody User user){
        userService.createNewUser(user);
        return true;
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if(username!=null){
            return new ResponseEntity<>(userService.updateUser(username, user), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
