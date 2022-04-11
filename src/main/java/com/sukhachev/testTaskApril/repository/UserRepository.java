package com.sukhachev.testTaskApril.repository;

import com.sukhachev.testTaskApril.model.User;
import org.springframework.stereotype.Repository;
import java.util.List;

//Интерфйес, описывающий взаимодействие с БД
@Repository
public interface UserRepository{

      List<User> getAllUsers();

      void save(User user);

      User find(long id);

      void update(User user, long id);

      void delete(long id);

      User findUserByUsername(String username);

}
