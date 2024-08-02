package com.sahil.SplitwiseApp.service;

import com.sahil.SplitwiseApp.model.Users;
import com.sahil.SplitwiseApp.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepo repo;

    // adding a user to the users table
    public void addUser(Users user){
        repo.save(user);
    }

    // Get user with user id
    public Users getUserById(int userId){
        System.out.println("in user service");
        return repo.findById(userId).orElse(null);
    }
}
