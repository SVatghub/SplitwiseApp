package com.sahil.SplitwiseApp.service;

import com.sahil.SplitwiseApp.model.Users;
import com.sahil.SplitwiseApp.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepo repo;
    
    public Users addUser(Users user){
        return repo.save(user);
    }

    public Users getUserById(int userId){
        return repo.findById(userId).orElse(null);
    }
}
