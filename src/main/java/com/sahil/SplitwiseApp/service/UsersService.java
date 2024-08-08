package com.sahil.SplitwiseApp.service;

import com.sahil.SplitwiseApp.DTO.UsersRequestDTO;
import com.sahil.SplitwiseApp.DTO.UsersResponseDTO;
import com.sahil.SplitwiseApp.model.Users;
import com.sahil.SplitwiseApp.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersService {

    @Autowired
    private UsersRepo repo;
    
    public UsersResponseDTO addUser(UsersRequestDTO usersRequestDTO){
        Users addedUser =  repo.save(Users.builder()
                .email(usersRequestDTO.getEmail())
                .name(usersRequestDTO.getName())
                .build());
        return UsersResponseDTO.builder()
                .name(addedUser.getName())
                .createdAt(addedUser.getCreatedAt())
                .email(addedUser.getEmail())
                .userId(addedUser.getUserId())
                .build();
    }

    public UsersResponseDTO getUserById(int userId){
        Users user = repo.findById(userId).orElse(null);
        if(user != null){
            return UsersResponseDTO.builder()
                    .name(user.getName())
                    .createdAt(user.getCreatedAt())
                    .email(user.getEmail())
                    .userId(user.getUserId())
                    .build();
        }
        return null;
    }

    public Integer getUserIdByNameAndEmail(String name, String email) {
        return repo.getUserIdByNameAndEmail(name,email);
    }

    public List<UsersResponseDTO> getAllUsers() {
        List<Users> usersList = repo.findAll();
        return usersList.stream()
                .map(user -> UsersResponseDTO.builder()
                        .name(user.getName())
                        .createdAt(user.getCreatedAt())
                        .email(user.getEmail())
                        .userId(user.getUserId())
                        .build())
                .collect(Collectors.toList());
    }
}
