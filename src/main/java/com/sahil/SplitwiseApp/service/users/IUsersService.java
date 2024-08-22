package com.sahil.SplitwiseApp.service.users;

import com.sahil.SplitwiseApp.DTO.nonExceptionDTOs.UsersRequestDTO;
import com.sahil.SplitwiseApp.DTO.nonExceptionDTOs.UsersResponseDTO;
import java.util.List;

public interface IUsersService {
    UsersResponseDTO addUser(UsersRequestDTO usersRequestDTO);
    UsersResponseDTO getUserById(int userId);
    Integer getUserIdByNameAndEmail(String name, String email);
    List<UsersResponseDTO> getAllUsers();
}
