package com.javatechie.group.service;

import com.javatechie.group.entity.User;
import com.javatechie.group.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class GroupUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    //Spring security solo entiende UserDetails no user, por eso hay que mapearlo
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUserName(username);
        //Para mapear el usuario a tipo UserDetails
        return user.map(GroupUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " Not Found"));
    }
}
