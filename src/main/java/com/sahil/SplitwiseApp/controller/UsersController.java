package com.sahil.SplitwiseApp.controller;

import com.sahil.SplitwiseApp.constants.ApiConstants;
import com.sahil.SplitwiseApp.model.Users;
import com.sahil.SplitwiseApp.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstants.BASE_URL + "/users")
public class UsersController {

    @Autowired
    private UsersService service;

    @PostMapping
    public Users addUser(@RequestBody Users user){
        return service.addUser(user);
    }

    @GetMapping("/{user-Id}")
    public Users getUserById(@PathVariable("user-Id") int userId){
        return service.getUserById(userId);
    }
}
