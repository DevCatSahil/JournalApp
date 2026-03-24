package com.sahil.JournalEntry.service;

import com.sahil.JournalEntry.Repository.UserRepository;
import com.sahil.JournalEntry.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceConfig implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepository.findByUsername(username);

        if (user!=null){
            return org.springframework.security.core.userdetails.User.builder().username(user
                            .getUsername())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0])).build();
        }else {
            throw new UsernameNotFoundException("User not Found with username " + username);
        }
    }
}













