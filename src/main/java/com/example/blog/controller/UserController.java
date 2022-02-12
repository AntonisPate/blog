package com.example.blog.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.model.User;
import com.example.blog.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value="/users", method=RequestMethod.POST)
    public HashMap<String, String> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @RequestMapping(value="/users/login", method=RequestMethod.POST)
    public Object loginUser(@RequestBody User user) {
        return userService.login(user);
    }

    @RequestMapping(value="/users", method=RequestMethod.GET)
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @RequestMapping(value="/users/{id}", method=RequestMethod.GET)
    public User getUser(@PathVariable(value = "id") Long id) {
        return userService.getUser(id);
    }

    @RequestMapping(value="/users/{id}", method=RequestMethod.PUT)
    public User updateUser(@PathVariable(value = "id") Long id, @RequestBody User userDetails) {
        return userService.updateUser(id, userDetails);
    }

    @RequestMapping(value="/users/{id}", method=RequestMethod.DELETE)
    public void deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUser(id);
    }
}
