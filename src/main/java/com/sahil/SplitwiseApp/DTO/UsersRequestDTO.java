package com.sahil.SplitwiseApp.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersRequestDTO {
    private String name;
    private String email;
}
