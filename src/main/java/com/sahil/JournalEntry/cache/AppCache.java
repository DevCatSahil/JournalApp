package com.sahil.JournalEntry.cache;

import com.sahil.JournalEntry.Repository.UserRepositoryImpl;
import com.sahil.JournalEntry.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    @Autowired
    private UserRepositoryImpl userRepository;

    public Map<String,Integer> appCache;

    @PostConstruct
    public void getSAUsers(){
        appCache = new HashMap<>();
         List<User> users = userRepository.getAllSAUsers();
         for(User user: users){
            appCache.put(user.getUsername(), appCache.getOrDefault(user.getUsername(),0)+1);
         }

    }

}
