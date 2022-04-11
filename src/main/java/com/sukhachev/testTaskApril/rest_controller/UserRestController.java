package com.sukhachev.testTaskApril.rest_controller;


import com.sukhachev.testTaskApril.model.User;
import com.sukhachev.testTaskApril.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/*
 В этом классе кнтроллеры, обрабатывающие rest запросы, обмен данными происходит в JSON.
 Реализованы все CRUD операци.
*/
@RestController
@RequestMapping("/api")
public class UserRestController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public void addUser(@RequestBody @Valid User user) {

        userService.save(user);

    }

    @PatchMapping("/users/{id}/edit")
    public void editUser(@PathVariable("id") long id, @RequestBody @Valid User user) {

        userService.update(user, id);

    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") long id) {

        userService.delete(id);


    }

}
