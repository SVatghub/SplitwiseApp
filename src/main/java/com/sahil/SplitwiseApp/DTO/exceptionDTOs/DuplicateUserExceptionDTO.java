package com.sahil.SplitwiseApp.DTO.exceptionDTOs;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DuplicateUserExceptionDTO {
    String msg;
}
