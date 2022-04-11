package com.sukhachev.testTaskApril.repository;

import com.sukhachev.testTaskApril.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

//Имплементация UserRepository, Взаимдействует с БД
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from users", User.class)
                .getResultList();
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);

    }

    @Override
    public User find(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(User user, long id) {

        User dbUser = entityManager.find(User.class, id);

        dbUser.setPassword(user.getPassword());

        dbUser.setUsername(user.getUsername());

        dbUser.setLastName(user.getLastName());

        dbUser.setAdress(user.getAdress());

        dbUser.setDateOfBirth(user.getDateOfBirth());

        entityManager.merge(user);
    }

    @Override
    public void delete(long id) {
        User user = entityManager.find(User.class, id);
//        Проверяеться на null здесь т.к невозможно проверить на уровне сервиса
        if (user != null)
        entityManager.remove(user);
    }

    @Override
    public User findUserByUsername(String username) {

        Query query = entityManager.createQuery("select u from users u where u.username =: username");

        query.setParameter("username", username);

        User user = (User) query.getSingleResult();

        return entityManager.find(User.class, user.getId());

    }
}
