package com.apicrud.crud.controllers;

import com.apicrud.crud.models.UserModel;
import com.apicrud.crud.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping()
    public ArrayList<UserModel> getUsers() {
        return userService.getUsers();
    }

    @PostMapping()
    public UserModel saveUser(@RequestBody  UserModel user) {
        return this.userService.saveUser(user);
    }

    @GetMapping(path = "/{id}")
    public Optional<UserModel> getUserById(@PathVariable("id") Long id) {
        return this.userService.getUserById(id);
    }

    @GetMapping("/query")
    public ArrayList<UserModel> getUserByPriority(@RequestParam("priority") Integer priority) {
        return this.userService.getUserPriority(priority);
    }

    @DeleteMapping(path = "/{id}")
    public String deletUserById(@PathVariable("id") Long id) {
        boolean ok = this.userService.deletUser(id);
        if (ok) {
            return "User deleted " + id;
        } else {
            return "User not found " + id;
        }
    }
}
