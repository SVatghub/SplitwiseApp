package com.sahil.SplitwiseApp.controller;

import com.sahil.SplitwiseApp.model.Users;
import com.sahil.SplitwiseApp.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("SplitwiseApp")
public class UsersController {

    @Autowired
    private UsersService service;

    @PostMapping("users")
    public void addUser(@RequestBody Users user){
        service.addUser(user);
    }

    @GetMapping("users/{userId}")
    public Users getUserById(@PathVariable int userId){
        return service.getUserById(userId);
    }
}
