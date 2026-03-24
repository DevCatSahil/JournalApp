package com.sahil.JournalEntry.controllers;

import com.sahil.JournalEntry.model.User;
import com.sahil.JournalEntry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class publicController {

    @Autowired
    private UserService userService;

    @PostMapping
    public boolean newUser(@RequestBody User user){
        userService.createNewUser(user);
        return true;
    }
}
