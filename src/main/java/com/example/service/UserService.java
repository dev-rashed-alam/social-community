package com.example.service;

import com.example.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var userFromDb = userDao.findByUserEmail(email);
        List<GrantedAuthority> authorities = new java.util.ArrayList<>(Collections.emptyList());
        authorities.add((GrantedAuthority) () -> userFromDb.getRole().name());
        return new User(userFromDb.getName(), userFromDb.getPassword(), authorities);
    }
}
