package com.sukhachev.testTaskApril.service;

import com.sukhachev.testTaskApril.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();

    void save(User user);

    User find(long id);

    void update(User user, long id);

    void delete(long id);


}
