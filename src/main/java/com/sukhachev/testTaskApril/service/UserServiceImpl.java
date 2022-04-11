package com.sukhachev.testTaskApril.service;

import com.sukhachev.testTaskApril.model.Role;
import com.sukhachev.testTaskApril.model.User;
import com.sukhachev.testTaskApril.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

//Сервисный слой приложения, имплементирует UserService и UserDetailsService
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findUserByUsername(username);

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                user.getAuthorities());
    }

    private Collection<? extends GrantedAuthority> mapRolesToauthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList());
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }


    @Override
    public void save(User user) {
        if (user != null)
        userRepository.save(user);
    }

    @Override
    public User find(long id) {
        return userRepository.find(id);
    }

    @Override
    public void update(User user, long id) {
        if (user != null)
        userRepository.update(user, id);

    }



    @Override
    public void delete(long id) {
        userRepository.delete(id);
    }


}

