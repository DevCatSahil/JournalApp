package com.sahil.JournalEntry.controllers;

import com.sahil.JournalEntry.model.User;
import com.sahil.JournalEntry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("get-users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public boolean newUser(@RequestBody User user){
        userService.createNewAdmin(user);
        return true;
    }

}
