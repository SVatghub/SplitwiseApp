package com.sahil.SplitwiseApp.DTO;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponseDTO {
    private int userId;
    private String name;
    private String email;
    private Timestamp createdAt;
}
