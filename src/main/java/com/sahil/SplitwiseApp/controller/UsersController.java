package com.sahil.SplitwiseApp.controller;

import com.sahil.SplitwiseApp.DTO.UsersRequestDTO;
import com.sahil.SplitwiseApp.DTO.UsersResponseDTO;
import com.sahil.SplitwiseApp.constants.ApiConstants;
import com.sahil.SplitwiseApp.service.users.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(ApiConstants.BASE_URL + "/users")
public class UsersController {

    @Autowired
    private IUsersService service;

    @PostMapping
    public UsersResponseDTO addUser(@RequestBody UsersRequestDTO usersRequestDTO){
        return service.addUser(usersRequestDTO);
    }

    @GetMapping("/{user-Id}")
    public UsersResponseDTO getUserById(@PathVariable("user-Id") int userId){
        return service.getUserById(userId);
    }

    @GetMapping("/{name}/{email}")
    public Integer getUserIdByNameAndEmail(@PathVariable String name, @PathVariable String email){
        return service.getUserIdByNameAndEmail(name,email);
    }

    @GetMapping
    public List<UsersResponseDTO> getAllUsers(){
        return service.getAllUsers();
    }
}
