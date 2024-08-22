package com.sahil.SplitwiseApp.service.users;

import com.sahil.SplitwiseApp.DTO.nonExceptionDTOs.UsersRequestDTO;
import com.sahil.SplitwiseApp.DTO.nonExceptionDTOs.UsersResponseDTO;
import com.sahil.SplitwiseApp.model.Users;
import com.sahil.SplitwiseApp.repo.UsersRepo;
import com.sahil.SplitwiseApp.validation.UsersValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersService implements IUsersService{

    @Autowired
    private UsersRepo repo;

    @Autowired
    private UsersValidation usersValidation;

    public UsersResponseDTO addUser(UsersRequestDTO usersRequestDTO) {
        usersValidation.validateUser(usersRequestDTO.getName(), usersRequestDTO.getEmail());
        usersValidation.isDuplicateEmail(usersRequestDTO.getEmail());

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
        usersValidation.existsById(userId);
        Users user = repo.findById(userId);

        return UsersResponseDTO.builder()
                .name(user.getName())
                .createdAt(user.getCreatedAt())
                .email(user.getEmail())
                .userId(user.getUserId())
                .build();
    }

    public Integer getUserIdByNameAndEmail(String name, String email) {
        usersValidation.validateUser(name, email);
        usersValidation.existsByNameAndEmail(name,email);
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
