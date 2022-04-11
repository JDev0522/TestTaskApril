package com.sukhachev.testTaskApril.controller;

import com.sukhachev.testTaskApril.model.User;
import com.sukhachev.testTaskApril.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/*
 В этом классе кнтроллеры, обрабатывающие запросы от web интерфейса. view реализован с помощью Thymeleaf
*/
@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;




    @Autowired
    public void setUserService(UserService userService) {

        this.userService = userService;
    }


    //Выводит всех пользователей на view
    @GetMapping()
    public String showAllUsers(Model model) {

        model.addAttribute("users", userService.getAllUsers());


        return "users/users";
    }

    //Форма редактирования пользователя
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.find(id));
        return "users/edit";
    }


    //Форма создания нового пользователя
    @GetMapping("/new")
    public String createUser(Model model) {
        model.addAttribute("user", new User());

        return "users/new";

    }

    //Валидация формы и создание нового пользователя
    @PostMapping()
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "users/new";

        userService.save(user);


        return "users/success";
    }
    //Валидация формы и внесение изменений в запись
    @PostMapping("/edited/{id}")
    public String editUser(@PathVariable("id") long id, @Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "users/edit";

        userService.update(user, id);

        return "users/success";

    }
    //Удаление пользвателя
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {

        userService.delete(id);

        return "users/success";
    }


}
